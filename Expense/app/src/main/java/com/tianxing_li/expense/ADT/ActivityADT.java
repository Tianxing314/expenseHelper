package com.tianxing_li.expense.adt;

public class ActivityADT {

    private String name;
    private String type;
    private String amount;
    private String date;
    private String comment;
    private String[] image;
    private int numImgSaved;

    public ActivityADT(String name, String type, String amount, String date, String comment, String[] image) {

        this.name = name;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.comment = comment;
        this.image = new String[3];
        this.numImgSaved = 0;
    }


    public void addImage(String imageName) {
        if (numImgSaved < 3) {
            image[numImgSaved] = imageName;
            numImgSaved += 1;
        }
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


    public String getComment() {
        return comment;
    }


    public String[] getImage() {
        return image;
    }


    public int getNumImgSaved() {
        return numImgSaved;
    }

}