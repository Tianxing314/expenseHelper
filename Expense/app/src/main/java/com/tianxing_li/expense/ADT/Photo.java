package com.tianxing_li.expense.adt;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

public class Photo {
    public static int count=0;
    public static int NEW = 1;
    public static int ASSIGNED = 2;
    private int status;
    private Bitmap bitmap;
    private String photoName;
    private int id;

    public Photo() {
        count++;
        this.id = count;
        this.status = NEW;
    }

    public int getId() { return id; }

    public void setAssigned() { this.status=ASSIGNED; }

    public int getStatus() { return status; }

    public void setBitmap(Bitmap data) { this.bitmap = data; }

    public Bitmap getBitmap() { return this.bitmap; }

    public String getPhotoName() { return this.photoName; }

    public void setPhotoName(String name) { this.photoName = name; }
}
