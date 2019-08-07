package com.tianxing_li.expense.io;

import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.adt.ActivityClassADT;

import java.io.File;
import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;
import static com.tianxing_li.expense.io.TextWriter.saveText;

public class DeleteActivityClass {

    //delete activityClass in "activity_class_name_file" File
    public static void deleteActivityClass (Activity activity, ActivityClassADT activityClassADT) {
        //load the existing activity_class_name_file content that contains all activityClassName
        //String existingFileContent = "";
        //loadText(activity, "activity_class_name_file", existingFileContent);
        ArrayList<String> existingFileContent = loadText(activity, "activity_class_name_file");

        //delete the existing activityClass in "activity_class_name_file" File
        String newFileContent = "";
        for (int i = 0; i < existingFileContent.size(); i++) {
            String activityClassName = existingFileContent.get(i).split(",")[0];
            if (!activityClassName.equals(activityClassADT.getActivityClass())) {
                Log.i("ddd", "1 " + activityClassName);
                Log.i("ddd", "2 " + activityClassADT.getActivityClass());
                newFileContent = newFileContent + existingFileContent.get(i) + "\n";
            }
        }
        saveText(activity, newFileContent, "activity_class_name_file");

        //delete activityClassFile that used to store activityADT belongs to it
        String fileName = activityClassADT.getActivityClass() + activityClassADT.getTime();
        File dir = activity.getFilesDir();
        File file = new File(dir, fileName);
        file.delete();

    }
}
