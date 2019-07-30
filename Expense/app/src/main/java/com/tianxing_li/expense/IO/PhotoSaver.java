package com.tianxing_li.expense.io;

import android.app.Activity;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PhotoSaver {

    public static void saveImg(Activity activity, String imgName, Bitmap bitmap) {
        OutputStream outputStream = null;

        try {
            File dataDir = activity.getDataDir();
            File imgDir = new File(dataDir.getAbsolutePath()+"/img/");
            imgDir.mkdir();
            File file = new File(imgDir, imgName);
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
