package com.cricfant.constant;

public enum Skill {
    BATSMAN(1),
    BOWLER(2),
    KEEPER(3),
    ALL_ROUNDER(4);

    private final int id;

    Skill(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
