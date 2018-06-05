package com.cricfant.constant;

public enum MatchResult {
    TEAM1_WIN(1),
    TEAM2_WIN(2),
    TIE(3),
    ABANDONED(4);

    private final int id;

    MatchResult(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
