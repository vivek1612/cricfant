package com.cricfant.constant;

public enum PowerType {
    BATTING(1),
    BOWLING(2);

    private final int id;

    PowerType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
