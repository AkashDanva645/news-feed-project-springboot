package com.example.posts_service.entities.enums;


public enum UserFollowingStatus {
    APPROVED("approved"), REQUESTED("requested");

    private String displayName;

    UserFollowingStatus(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static UserFollowingStatus getByEnumName(String val) {
        return switch (val) {
            case "APPROVED" -> UserFollowingStatus.APPROVED;
            case "REQUESTED" -> UserFollowingStatus.REQUESTED;
            default -> null;
        };
    }
}
