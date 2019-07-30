package com.tianxing_li.expense.io;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class PhotoLoader {

    public static Bitmap loadImg(Activity activity, String imgName) {
        File dataDir = activity.getDataDir();
        File imgDir = new File(dataDir.getAbsolutePath() + "/img/");
        File file = new File(imgDir, imgName);
        String path = file.getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }
}
