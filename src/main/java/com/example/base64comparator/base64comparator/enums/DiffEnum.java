package com.example.base64comparator.base64comparator.enums;

/**
 * Enum for Difference Messages.
 */
public enum DiffEnum {
    EQUAL("These two json objects are equal"),
    NOT_EQUAL_SIZE("These two json objects are not at the same size"),
    DIFFERENT("These two json have the same size but different content");
    private String val;

    DiffEnum(String val) {
        this.val = val;
    }
    public String getVal() {
        return val;
    }
}
