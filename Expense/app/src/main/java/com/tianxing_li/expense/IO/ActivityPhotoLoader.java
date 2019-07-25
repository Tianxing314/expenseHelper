package com.tianxing_li.expense.IO;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.tianxing_li.expense.ADT.ActivityADT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ActivityPhotoLoader {

    //imgName should include extension .png
    public static void loadImageFromStorage(ImageView img, ActivityADT activityADT) {
        String imgName = (activityADT.getActivityClass()
                         + activityADT.getName()
                         + activityADT.getTime()
                         + activityADT.getImgOrder() + ".png");
        String profileImageDir = "/data/user/0/com.tianxing_li.expense/app_imageDir";
        String path = profileImageDir;
        File file = new File(path, imgName);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            img.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            Log.i("Sky", "FileNotFound Loader");
            e.printStackTrace();
        }
    }
}



