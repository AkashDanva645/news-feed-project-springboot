package com.example.user_service.entities.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("regular-user");

    private final String value;

    UserRole(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }
}
