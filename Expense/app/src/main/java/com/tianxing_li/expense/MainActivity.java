package com.tianxing_li.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.tianxing_li.expense.IO.InitializeFiles;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton floatingBTN;
    private Button accountBTN;
    private Map<String, String> settingsMap;
    public static final String FIRST_TIME_OPEN = "pref_first_use_note_key";
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if this is the first time open the app
        /*
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sharedPreferences.getBoolean(FIRST_TIME_OPEN, true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_TIME_OPEN, false);
            editor.apply();

            //create and initialize all files
            InitializeFiles.initialzeFile(this);
        }
        */
        SharedPreferences settings = getSharedPreferences(FIRST_TIME_OPEN, 0);
        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Sky", "First time");

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
            InitializeFiles.initialzeFile(this);
        }
        else {
            Log.i("Sky", "Not");

        }

        toolbar = findViewById(R.id.toolbar_main_hold_tools);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.vp_main_hold_tab_pages);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //listen to fragment change event
        //for future update use
        /*
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
        });
        */

        tabLayout = findViewById(R.id.th_main_hold_tab_heads);
        tabLayout.setupWithViewPager(viewPager);

        accountBTN = findViewById(R.id.btn_main_account);
        accountBTN.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.ic_search:
                msg = "search selected";
                break;
            case R.id.main_menu_option1:
                msg = "main menu option1 seleted";
                break;
            case R.id.main_menu_option2:
                msg = "main menu option2 seleted";
                break;
            case R.id.main_menu_option3:
                msg = "main menu option3 seleted";
                break;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == accountBTN) {
            Intent intent = new Intent();
            intent.setClass(this, SettingActivity.class);
            startActivity(intent);
        }
    }
}
