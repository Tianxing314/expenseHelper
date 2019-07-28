package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.ADT.ActivityADT;

import java.util.ArrayList;


public class NotsubmitActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    FloatingActionButton floatingBTN;
    Button backBTN;
    NotsubmitAdapter adapter;
    ArrayList<ActivityADT> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notsubmit);

        listView = findViewById(R.id.lv_notsubmit);

        //fab
        floatingBTN = findViewById(R.id.fab_notsubmit_add_activity);
        floatingBTN.setOnClickListener(this);

        //back button
        backBTN = findViewById(R.id.btn_notsubmit_back);
        backBTN.setOnClickListener(this);

        //prep date
        //TODO change to real load date method
        //#######################
        String activityClass = "Beijing Trip";
        String name = "Air China";
        String type = "transportation";
        String amount = "$314,159.26";
        String date = "20190727";
        String time = "202935";//change to the time that activity class was created
        String comment = "Nice Trip!";
        String state = "Beijing Trip";


        //#######################
        //TODO get the fileName passed from Fragment1 intent and load the list based on that
        list = new ArrayList<ActivityADT>();
        list.add(new ActivityADT(activityClass, name, type, amount, date, time, comment, state));
        //#######################

        adapter = new NotsubmitAdapter(this);
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == backBTN) {
            finish();
        }
        //TODO add addActivity function
        else if (view == floatingBTN) {
            Toast.makeText(this,"FAB", Toast.LENGTH_SHORT).show();
        }


    }
}

