package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.tianxing_li.expense.io.SettingsReader;
import com.tianxing_li.expense.io.SettingsWriter;

import java.util.Map;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button backBTN;
    private Button accountBTN;
    private Switch switchBTN;
    private Spinner currencySpinner;

    private boolean setting_notification;
    private String selectedCurrency;

    //Store values get from setting file
    private SettingsReader settingsReader;
    private Map<String, String> settingMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set notification
        settingsReader = new SettingsReader(this);
        settingMap = settingsReader.getSettingsMap();

        if (settingsReader.getNotificationSetting().equals("off")) {
            setting_notification = false;
        }
        else {
            setting_notification = true;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backBTN = findViewById(R.id.btn_settings_back);
        backBTN.setOnClickListener(this);

        accountBTN = findViewById(R.id.btn_settings_goto_set_account);
        accountBTN.setOnClickListener(this);

        //Control switch button and the logics in each switch state
        switchBTN = findViewById(R.id.switch_settings_control_notification);

        //set notification switch state according to config and permission
        //cannot change to if (setting_notification) because it might be null;
        if (setting_notification==true) {
            //force turn off the notification switch if notification permission is NOT granted
            if (!NotificationManagerCompat.from(getBaseContext()).areNotificationsEnabled()) {
                switchBTN.setChecked(false);

                settingMap.replace("notification", "off");
                SettingsWriter.saveSettings(this, settingMap);

                setting_notification = false;
            }
            switchBTN.setChecked(true);
        }

        //set logic for switch state
        switchBTN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    //check is notification permission is granted, if not,open device setting page
                    //SDK version must >=24
                    if (!NotificationManagerCompat.from(getBaseContext()).areNotificationsEnabled()) {
                        switchBTN.setChecked(false);
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
                        startActivity(intent);
                    }
                    else {
                        setting_notification = true;
                        //update notification config to "on" (true) in config file
                        settingMap.replace("notification", "on");
                        SettingsWriter.saveSettings(SettingActivity.this, settingMap);
                    }
                }
                else {
                    //update notification config to "off" (false) in config file
                    settingMap.replace("notification", "off");
                    SettingsWriter.saveSettings(SettingActivity.this, settingMap);
                    setting_notification = false;
                }
            }
        });

        //construct spinner for currency selection
        currencySpinner = findViewById(R.id.spinner_settings_currency);
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(currencyAdapter);

        //set selectedCurrency and spinner according to configs in setting file
        currencySpinner.setOnItemSelectedListener(this);
        selectedCurrency = settingsReader.getCurrencySetting();
        //currency default to CAD$ at position 0
        int spinnerPosition = 0;
        switch (selectedCurrency) {
            case "USD$":
                spinnerPosition = 1;
                break;
            case "CNY¥":
                spinnerPosition = 2;
                break;
        }
        //update currency spinner
        currencySpinner.setSelection(spinnerPosition);
    }

    @Override
    public void onClick(View view) {

        if (view == backBTN) {
            finish();
        }
        else if (view == accountBTN) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //For currency spinner when item is selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //store the selected currency to config
        selectedCurrency = currencySpinner.getSelectedItem().toString();
        settingMap.replace("currency", selectedCurrency);
        SettingsWriter.saveSettings(this, settingMap);
    }

    //For currency spinner when no item is selected
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}
