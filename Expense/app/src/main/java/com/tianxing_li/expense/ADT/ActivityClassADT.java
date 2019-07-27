package com.tianxing_li.expense.ADT;

public class ActivityClassADT {
    private String activityClass;
    private String state;
    private String time;

    public ActivityClassADT(String activityClass, String state, String time) {

        this.activityClass = activityClass;
        this.state = state;
        this.time = time;
    }

    public String getActivityClass() {
        return activityClass;
    }

    public String getState() {
        return state;
    }

    public String getTime() { return time; }
}
