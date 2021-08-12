package com.alchemy.registration.roles;

public enum ApplicationUserPermission {
    USER_ADD("user:register"),
    USER_MODIFY("admin:modify"),
    USER_DELETE("admin:deletes");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
