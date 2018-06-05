package com.cricfant.constant;

public enum Dismissal {

    NOT_OUT(0), BOWLED(1), CAUGHT(2), LBW(3), RUN_OUT(4), STUMPED(5),
    HIT_WICKET(6), OBS_FIELD(7), DOUBLE_HIT(8), TIMED_OUT(9), HAND_BALL(10), RETIRED_HURT(11), DNB(12), ABSENT_HURT(13);

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isOut(){
        return this.id>0 && this.id<12;
    }

    Dismissal(Integer id) {
        this.id = id;
    }
}
