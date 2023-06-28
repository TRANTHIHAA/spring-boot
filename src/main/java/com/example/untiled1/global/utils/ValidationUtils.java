package com.example.untiled1.global.utils;

import com.example.untiled1.global.dto.ValidationFieldError;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class ValidationUtils {
    private ValidationUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> List<ValidationFieldError> validateAndToListFieldError(T t) {
        List<ValidationFieldError> validationFieldErrors = new ArrayList<>();
        try {
            Validator validator = initialValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(t);

            String xmlRootElement = t.getClass().getSimpleName();
            for (ConstraintViolation<T> constraintViolation : violations) {
                ValidationFieldError validationFieldError = new ValidationFieldError();
                validationFieldError.setField(xmlRootElement + "." + constraintViolation.getPropertyPath());
                validationFieldError.setMessage(constraintViolation.getMessage());

                validationFieldErrors.add(validationFieldError);
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }
        return validationFieldErrors;
    }

    private static Validator initialValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        return factory.getValidator();
    }
}
