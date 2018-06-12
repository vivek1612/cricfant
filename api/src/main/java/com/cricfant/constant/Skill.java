package com.cricfant.constant;

public enum Skill {
    BATSMAN(1),
    KEEPER(2),
    ALL_ROUNDER(3),
    BOWLER(4);

    private final int id;

    Skill(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
