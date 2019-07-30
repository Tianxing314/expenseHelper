package com.tianxing_li.expense.io;

import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.adt.ActivityClassADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;
import static com.tianxing_li.expense.io.TextWriter.saveText;

public class ChangeState {

    //change the state of activityClass according to the operation in "activity_class_name_file" File
    public static void changeState(Activity activity, ActivityClassADT activityClassADT, String newState) {
        //load the existing activity_class_name_file content that contains all activityClassName
        //String existingFileContent = "";
        //loadText(activity, "activity_class_name_file", existingFileContent);
        ArrayList<String> existingFileContent = loadText(activity, "activity_class_name_file");

        //delete the existing activityClass with current state
        String newFileContent = "";
        for (int i = 0; i < existingFileContent.size(); i++) {
            String activityClassName = existingFileContent.get(i).split(",")[0];
            if (!activityClassName.equals(activityClassADT.getActivityClass())) {
                Log.i("skyli", activityClassName);
                Log.i("skyli", activityClassADT.getActivityClass());
                newFileContent = newFileContent + existingFileContent.get(i) + "\n";
            }
        }
        //append the existing activityClass with updated new state
        newFileContent = newFileContent  + activityClassADT.getActivityClass() + "," + newState + "," + activityClassADT.getTime() + "\n";
        saveText(activity, newFileContent, "activity_class_name_file");
    }
}
