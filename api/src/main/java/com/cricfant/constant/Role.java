package com.cricfant.constant;

public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
