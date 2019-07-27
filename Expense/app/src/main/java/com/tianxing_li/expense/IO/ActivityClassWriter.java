package com.tianxing_li.expense.IO;

import android.app.Activity;

import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.IO.TextReader.loadText;
import static com.tianxing_li.expense.IO.TextWriter.saveText;

public class ActivityClassWriter {

    public static boolean saveActivityClass(Activity activity, ActivityClassADT activityClassADT) {
        String activityClassName = activityClassADT.getActivityClass();
        String state = activityClassADT.getState();

        //return false if filename already exist
        if (loadText(activity, activityClassName) != null) {
            return false;
        }
        //create an empty file with name = activityClassName
        //This empty file will be used to save activityADTs that belong to activityClass
        saveText(activity, "", activityClassName);

        //load the existing activity_class_name_file content that contains all activityClassName
        //String existingFileContent = "";
        //loadText(activity, "activity_class_name_file", existingFileContent);
        ArrayList<String> existingFileContent = loadText(activity, "activity_class_name_file");

        if (existingFileContent.equals("[]")) {
            String newFileContent = activityClassName + "," + state + "\n";
            saveText(activity, newFileContent, "activity_class_name_file");
        }
        //append the new activityClassName to activity_class_name_file
        else {
            String newFileContent = "";
            for (int i = 0; i < existingFileContent.size(); i++) {
                newFileContent += existingFileContent.get(i);
            }
            newFileContent = newFileContent  + activityClassName + "," + state + "\n";
            saveText(activity, newFileContent, "activity_class_name_file");
        }


        return true;
    }
}
