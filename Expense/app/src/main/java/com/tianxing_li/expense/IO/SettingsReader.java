package com.tianxing_li.expense.io;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SettingsReader {
    final String fileName = "settings";
    private Map<String, String> settingsMap;

    public SettingsReader(Activity activity) {
        settingsMap = new HashMap();
        FileInputStream fis = null;
        try {
            fis = activity.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                settingsMap.put(lineData[0], lineData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Map<String, String> getSettingsMap() {
        return settingsMap;
    }

    public String getNotificationSetting() {
        return settingsMap.get("notification");
    }

    public String getCurrencySetting() {
        return settingsMap.get("currency");
    }

}
