package com.tianxing_li.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

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
import com.tianxing_li.expense.IO.SettingsWriter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton floatingBTN;
    private Button accountBTN;
    private Map<String, String> settingsMap;
    public static final String KEY_FIRST_USE_NOTE = "pref_first_use_note_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if this is the first time open the app
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sharedPreferences.getBoolean(KEY_FIRST_USE_NOTE, true)) {
            Log.i("Sky", "first Time");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_FIRST_USE_NOTE, false);
            editor.apply();
            //write default setting file
            settingsMap = new HashMap<>();
            SettingsWriter.saveSettings(this, settingsMap);
        }
        else {
            Log.i("Sky", "Not");

        }

        toolbar = findViewById(R.id.toolbar_main_hold_tools);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        floatingBTN = findViewById(R.id.fab_main_add_expense_class);
        floatingBTN.setOnClickListener(this);

        viewPager = findViewById(R.id.vp_main_hold_tab_pages);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //listen to fragment change event
        //control floatingBTN hide/show
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    floatingBTN.show();
                } else {
                    floatingBTN.hide();
                }
            }
        });



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
        //for add button
        if (view == floatingBTN) {
            Toast.makeText(this, "FAB selected", Toast.LENGTH_SHORT).show();
        }
        else if (view == accountBTN) {
            Intent intent = new Intent();
            intent.setClass(this, SettingActivity.class);
            startActivity(intent);
        }
    }
}
