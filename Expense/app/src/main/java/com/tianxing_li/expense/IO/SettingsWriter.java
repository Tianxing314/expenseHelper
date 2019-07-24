package com.tianxing_li.expense.IO;

import android.app.Activity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SettingsWriter {


    public static void saveSettings(Activity activity, Map<String, String> settingsMap) {
        final String fileName = "settings";
        String data = "";
        FileOutputStream fos = null;

        //prepare data
        if (settingsMap.isEmpty()) {
            data = "notification,off\ncurrency,CAD$\n";
        }
        else {
            Set<String> keys = settingsMap.keySet();

            for (String key:keys) {
                data += key + "," + settingsMap.get(key) + "\n";
            }
        }


        //write data into list file
        try {
            fos = activity.openFileOutput(fileName, activity.MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
