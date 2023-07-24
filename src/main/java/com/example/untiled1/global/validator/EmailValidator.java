package com.example.untiled1.global.validator;

import com.example.untiled1.global.constants.Regex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() < 1) {
            return true;
        }
        Pattern pattern = Pattern.compile(Regex.REGEX_EMAIL);
        Matcher matcher = pattern.matcher(value);
        try {
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

}

