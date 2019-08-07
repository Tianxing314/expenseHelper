package com.tianxing_li.expense.io;

import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.adt.ActivityADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.TextReader.loadText;
import static com.tianxing_li.expense.io.TextWriter.saveText;

public class DeleteActivity {

    public static void deleteActivity(Activity activity, ActivityADT activityADT, String activityClassName, String time) {

        String fileName = activityClassName + time;
        //load the existing activities
        ArrayList<String> existingFileContent = loadText(activity, fileName);

        //delete the existing activityClass with current state
        String newFileContent = "";
        for (int i = 0; i < existingFileContent.size(); i++) {
            //construct new activityADT based on file reading;
            String name = existingFileContent.get(i).split(",")[0].replace("wd4sky", ",");
            String type = existingFileContent.get(i).split(",")[1];
            String amount = existingFileContent.get(i).split(",")[2];
            String date = existingFileContent.get(i).split(",")[3];
            String comment = existingFileContent.get(i).split(",")[4].replace("wd4sky", ",");
            String[] image = {"", "", ""};
            ActivityADT tempActivityADT = new ActivityADT(name, type, amount, date, comment, image);
            for (int j=0; j<3; j++) {
                Log.i("xxx", existingFileContent.get(i));
                tempActivityADT.addImage(existingFileContent.get(i).split(",")[5+j]);
            }


            if (!tempActivityADT.equals(activityADT)) {
                newFileContent = newFileContent + existingFileContent.get(i) + "\n";
            }
        }
        saveText(activity, newFileContent, fileName);
    }
}
