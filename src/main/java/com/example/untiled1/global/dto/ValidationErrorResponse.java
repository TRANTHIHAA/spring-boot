package com.example.untiled1.global.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ValidationErrorResponse extends ErrorResponseData {
    List<ValidationFieldError> fieldErrors;

    public ValidationErrorResponse() {
        super("VALIDATION_FAILED", "tham số không chính xác");
        this.fieldErrors = new ArrayList<>();
    }

    public ValidationErrorResponse(List<ValidationFieldError> fieldErrors) {
        super("VALIDATION_FAILED", "tham số không chính xác");
        this.fieldErrors = fieldErrors;
    }

    public void setFieldErrors(List<ValidationFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addFieldError(ValidationFieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValidationErrorResponse that = (ValidationErrorResponse) o;
        return Objects.equals(fieldErrors, that.fieldErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldErrors);
    }

}
