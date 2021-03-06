package com.github.gpor0.jooreo.exceptions;

/**
 * Author: gpor0
 */
public class UnsupportedParameterException extends IllegalArgumentException {

    private String field;
    private Object value;

    private Object[] fieldValues;

    public UnsupportedParameterException(String field, Object value, Object... fieldVal) {
        super("Unsupported value \"" + value + "\" for field " + field + " " + (fieldVal != null ? fieldVal : ""));
        this.field = field;
        this.value = value;
        this.fieldValues = fieldVal;
    }

    public UnsupportedParameterException(String field) {
        super("Unsupported field " + field);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public Object[] getFieldValues() {
        return fieldValues;
    }
}
