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
        this.image = image;
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

    public boolean equals(ActivityADT targetActivityADT) {
        if (!this.getName().equals((targetActivityADT.getName()))) {
            return false;
        }
        if (!this.getType().equals((targetActivityADT.getType()))) {
            return false;
        }
        if (!this.getAmount().equals((targetActivityADT.getAmount()))) {
            return false;
        }
        if (!this.getDate().equals((targetActivityADT.getDate()))) {
            return false;
        }
        if (!this.getComment().equals((targetActivityADT.getComment()))) {
            return false;
        }
        if (!this.getImage()[0].equals((targetActivityADT.getImage()[0]))) {
            return false;
        }
        if (!this.getImage()[1].equals((targetActivityADT.getImage()[1]))) {
            return false;
        }
        if (!this.getImage()[2].equals((targetActivityADT.getImage()[2]))) {
            return false;
        }
        return true;
    }

}