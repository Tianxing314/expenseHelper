package com.tianxing_li.expense.io;

import android.app.Activity;

import com.tianxing_li.expense.adt.ActivityADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;


public class ActivityReader {

    //activityClassName and time got from fragment to determine the associate activities
    public static ArrayList<ActivityADT> loadActivity (Activity activity, String activityClassName, String time)  {
        ArrayList<ActivityADT> list = new ArrayList<>();

        String fileName = activityClassName + time;
        ArrayList<String> lineArray = loadText(activity, fileName);

        for (int i = 0; i < lineArray.size(); i++) {
            String[] lineElements = lineArray.get(i).split(",");

            String name = lineElements[0];
            String type = lineElements[1];
            String amount = lineElements[2];
            String date = lineElements[3];
            String comment = lineElements[4].replace(time, ",");//decode to time to ",";
            String img1 = lineElements[5];
            String img2 = lineElements[6];
            String img3 = lineElements[7];
            String[] image = {img1, img2, img3};
            ActivityADT activityADT = new ActivityADT(name, type, amount, date, comment, image);
            list.add(activityADT);

        }

        return list;
    }
}

