package com.tianxing_li.expense.ADT;

public class ActivityClassADT {
    private String activityClass;
    private String state;

    public ActivityClassADT(String activityClass, String state) {

        this.activityClass = activityClass;
        this.state = state;
    }

    public String getActivityClass() {
        return activityClass;
    }

    public String getState() {
        return state;
    }
}
