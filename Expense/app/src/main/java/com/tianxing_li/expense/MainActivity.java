package com.tianxing_li.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton floatingBTN;
    private Button accountBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        floatingBTN = findViewById(R.id.floatingBTN);
        floatingBTN.setOnClickListener(this);

        accountBTN = findViewById(R.id.accountBTN);
        accountBTN.setOnClickListener(this);

        /*
        //adapter
        //###########   ListView   #######################
        ListView lv = findViewById(R.id.lv_main);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("logo", R.drawable.img1);
        map.put("title", "Gmail");
        map.put("version", "version 8.4.0");
        map.put("size", "size: 32.81M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.img1);
        map.put("title", "YouTube");
        map.put("version", "version 2.4.1");
        map.put("size", "size: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.img1);
        map.put("title", "Chrome");
        map.put("version", "version 6.2.0");
        map.put("size", "size: 15.15M");
        list.add(map);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.setList(list);
        lv.setAdapter(myAdapter);

        //###########  End of ListView   #######################
        */
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
            Toast.makeText(this, "account BTN selected", Toast.LENGTH_SHORT).show();
        }
    }
}
