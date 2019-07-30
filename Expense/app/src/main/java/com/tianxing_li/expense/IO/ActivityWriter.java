package com.tianxing_li.expense.io;


import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.adt.ActivityADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;
import static com.tianxing_li.expense.io.TextWriter.saveText;

public class ActivityWriter {

    //finalName is the activityClassName that passed from previous activity
    public static boolean saveActivity(Activity activity, ActivityADT activityADT, String activityClassName, String time) {
        String name = activityADT.getName();
        String type = activityADT.getType();
        String amount = activityADT.getAmount();
        String date = activityADT.getDate();
        String comment = activityADT.getComment().replace(",", time);//replace comment with time to avoid string reading problem
        String img1 = activityADT.getImage()[0];
        String img2 = activityADT.getImage()[1];
        String img3 = activityADT.getImage()[2];

        String fileName = activityClassName + time;
        ArrayList<String> existingFileContent = loadText(activity, fileName);

        if (existingFileContent == null) {
            String newFileContent =  name + ","
                                     + type + ","
                                     + amount + ","
                                     + date + ","
                                     + comment + ","
                                     + img1 + ","
                                     + img2 + ","
                                     + img3 + "\n";
            saveText(activity, newFileContent, fileName);
        }
        //append the new activityClassName to activity_class_name_file
        else {
            String newFileContent = "";
            for (int i = 0; i < existingFileContent.size(); i++) {
                Log.i("Sky", existingFileContent.get(i)+"nextLine");
                newFileContent = newFileContent + existingFileContent.get(i) + "\n";
            }
            newFileContent = newFileContent
                           + name + ","
                           + type + ","
                           + amount + ","
                           + date + ","
                           + comment + ","
                           + img1 + ","
                           + img2 + ","
                           + img3 + "\n";


            saveText(activity, newFileContent, fileName);
        }
        return true;
    }
}
