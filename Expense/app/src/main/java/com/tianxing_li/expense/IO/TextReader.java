package com.tianxing_li.expense.IO;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextReader {

    public static ArrayList<String> loadText(Activity activity, String fileName) {
        FileInputStream fis = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            fis = activity.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            //StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                list.add(text);

            }
            return list;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO delete after test
            Log.i("Sky","TextReader.java FileNotFound");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO delete after test
            Log.i("Sky","TextReader.java IOException 1");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //TODO delete after test
                    Log.i("Sky","TextReader.java IOException 2");
                }
            }
        }
        return null;
    }
}
