package com.tianxing_li.expense.IO;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.util.Log;

import com.tianxing_li.expense.ADT.ActivityADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfilePhotoSaver {

    public static String saveToInternalStorage(Activity activity, Bitmap bitmapImage, ActivityADT activityADT) {
        ContextWrapper cw = new ContextWrapper(activity.getApplicationContext());
        //path to imageDir
        //path: "/data/user/0/com.tianxing_li.expense/app_imageDir"
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        //Create imageDir
        String imgName = (activityADT.getActivityClass()
                + activityADT.getName()
                + activityADT.getTime()
                + activityADT.getImgOrder() + ".png");
        File mypath = new File(directory, imgName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            //write image to output stream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            Log.i("Sky","FileNotFound Saver");
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //TODO delete test code below
        String path = directory.getAbsolutePath();
        Log.i("Sky", path);

        return directory.getAbsolutePath();
    }
}
