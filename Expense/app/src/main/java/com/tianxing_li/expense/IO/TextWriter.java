package com.tianxing_li.expense.IO;

import android.app.Activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public abstract class TextWriter {
    private String content;

    public static String saveText(Activity activity, String textContent, String fileName) {
        String text = textContent;
        FileOutputStream fos = null;

        try {
            fos = activity.openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return activity.getFilesDir() + "/" + fileName;
    }

}
