package com.tianxing_li.expense.ADT;

public class ActivityADT extends ActivityClassADT{

    private String name;
    private String type;
    private String amount;
    private String date;
    private String comment;
    private String[] image;
    private int numImgSaved;

    public ActivityADT(String activityClass, String name, String type, String amount, String date, String time, String comment, String state) {
        super(activityClass, state, time);
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.comment = comment;
        this.image = new String[3];
        this.numImgSaved = 0;
    }

    public void addImage() {
        if (numImgSaved < 3) {
            image[numImgSaved] = getActivityClass() + name + getTime() + (numImgSaved + 1) + ".png";
        }
    }

    public String getActivityClass() {
        return super.getActivityClass();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return super.getTime();
    }

    public String getComment() {
        return comment;
    }

    public String getState() {
        return super.getState();
    }

    public String[] getImage() {
        return image;
    }

    public int getNumImgSaved() {
        return numImgSaved;
    }

    public int getImgOrder() {
        return (numImgSaved + 1);
    }
}