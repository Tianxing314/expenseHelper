package com.tianxing_li.expense.IO;

import android.app.Activity;
import android.util.Log;

import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.util.ArrayList;

import static com.tianxing_li.expense.IO.TextReader.loadText;


public class ActivityClassReader {

    public static ArrayList<ActivityClassADT> loadActivityClass (Activity activity, String state)  {
        ArrayList<ActivityClassADT> list = new ArrayList<>();
        // check and create "activity_class_name_file" on the first time app is opened

        ArrayList<String> lineArray = loadText(activity, "activity_class_name_file");

        for (int i = 0; i < lineArray.size(); i++) {
            String[] lineElements = lineArray.get(i).split(",");
            ActivityClassADT activityClassADT = new ActivityClassADT(lineElements[0], lineElements[1]);
            Log.i("Sky", activityClassADT.getState());

            if(activityClassADT.getState().equals(state)) {
                Log.i("Sky", "here");
                list.add(activityClassADT);
            }
        }

        return list;
    }
}
