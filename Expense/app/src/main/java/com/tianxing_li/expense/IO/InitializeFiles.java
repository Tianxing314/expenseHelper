package com.tianxing_li.expense.IO;

import android.app.Activity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class InitializeFiles {

    public static void initialzeFile(Activity activity) {
        Log.i("Sky", "to here 1");

        //create settings (currency and notifications) file
        Map<String, String> settingsMap = new HashMap<>();
        SettingsWriter.saveSettings(activity, settingsMap);
        Log.i("Sky", "to here");

        //create "activity_class_name_file" file that used to stores all activityClassName
        String path = TextWriter.saveText(activity, "", "activity_class_name_file");
        Log.i("Sky", path);

    }
}

