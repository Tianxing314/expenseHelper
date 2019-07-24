package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import static android.provider.Settings.EXTRA_APP_PACKAGE;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button backBTN;
    private Button accountBTN;
    private Switch switchBTN;
    private Spinner currencySpinner;

    private boolean setting_notification;
    //###############################
    //setting_notification = config_notification (value retrieved from config file)
    //##############################
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backBTN = findViewById(R.id.setting_back_BTN);
        backBTN.setOnClickListener(this);

        accountBTN = findViewById(R.id.setting_account_BTN);
        accountBTN.setOnClickListener(this);

        switchBTN = findViewById(R.id.switchBTN);
        //set notification switch state according to config and permission
        //!!!!!!!need to add configuration to store user's setting
        //cannot change to if (setting_notification) because it might be null;
        if (setting_notification==true) {
            //force turn off the notification switch if notification permission is NOT granted
            if (!NotificationManagerCompat.from(getBaseContext()).areNotificationsEnabled()) {
                switchBTN.setChecked(false);

                //################
                //need to set config_notification to false in config file here
                //################

                setting_notification = false;
            }
            switchBTN.setChecked(true);
        }

        switchBTN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    //SDK version must >=24
                    if (!NotificationManagerCompat.from(getBaseContext()).areNotificationsEnabled()) {
                        switchBTN.setChecked(false);
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
                        startActivity(intent);
                        Log.d("sky", "on setting page");
                    }
                    else {
                        setting_notification = true;
                        //################
                        //need to set config_notification to true in config file here
                        //################
                        Toast.makeText(getBaseContext(), "ON", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //################
                    //need to set config_notification to false in config file here
                    //################
                    setting_notification = false;
                    Toast.makeText(getBaseContext(), "OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        currencySpinner = findViewById(R.id.currency_spinner);
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(currencyAdapter);
        currencySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == backBTN) {

            //option 1
            //slow, reload previous page
            //to any page
            //
            /*
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class );
            startActivity(intent);
            */


            //option 2
            //NavUtils.navigateUpFromSameTask(this);

            //option 3
            //fastest
            //item may lost
            finish();
        }
        else if (view == accountBTN) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //For currency spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT);
    }

    //For currency spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
