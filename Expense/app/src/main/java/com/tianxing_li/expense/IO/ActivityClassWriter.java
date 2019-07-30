package com.tianxing_li.expense.io;

import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.adt.ActivityClassADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;
import static com.tianxing_li.expense.io.TextWriter.saveText;

public class ActivityClassWriter {

    public static boolean saveActivityClass(Activity activity, ActivityClassADT activityClassADT) {
        String activityClassName = activityClassADT.getActivityClass();
        String state = activityClassADT.getState();
        String time = activityClassADT.getTime();

        //return false if filename already exist(barely happens)
        if (loadText(activity, (activityClassName + time)) != null) {
            return false;
        }
        //create an empty file with name = activityClassName + time
        //This empty file will be used to save activityADTs that belong to activityClass
        saveText(activity, "", (activityClassName + time));

        //load the existing activity_class_name_file content that contains all activityClassName
        //String existingFileContent = "";
        //loadText(activity, "activity_class_name_file", existingFileContent);
        ArrayList<String> existingFileContent = loadText(activity, "activity_class_name_file");

        if (existingFileContent.equals("[]")) {
            String newFileContent = activityClassName + "," + state + "," + time + "\n";
            saveText(activity, newFileContent, "activity_class_name_file");
        }
        //append the new activityClassName to activity_class_name_file
        else {
            String newFileContent = "";
            for (int i = 0; i < existingFileContent.size(); i++) {
                Log.i("Sky", existingFileContent.get(i)+"nextLine");
                newFileContent = newFileContent + existingFileContent.get(i) + "\n";
            }
            newFileContent = newFileContent  + activityClassName + "," + state + "," + time + "\n";
            saveText(activity, newFileContent, "activity_class_name_file");
        }


        return true;
    }
}
