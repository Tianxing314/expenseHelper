package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.adt.ActivityADT;
import com.tianxing_li.expense.io.DeleteActivity;

import java.util.ArrayList;

import static com.tianxing_li.expense.io.ActivityReader.loadActivity;


public class NotsubmitActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    FloatingActionButton floatingBTN;
    Button backBTN;
    NotsubmitAdapter adapter;
    ArrayList<ActivityADT> list;
    //get from frangment1
    String activityClass;
    String time;
    SwipeRefreshLayout pullToRefresh;
    TextView title;
    TextView appName;

    NotsubmitAdapter.NotsubmitItemListener deleteBTNListener = new NotsubmitAdapter.NotsubmitItemListener() {
        @Override
        public void myOnclick(int position, View view) {
            Toast.makeText(NotsubmitActivity.this, "delete ", Toast.LENGTH_SHORT).show();
            ActivityADT activityADT = list.get(position);
            DeleteActivity.deleteActivity(NotsubmitActivity.this, activityADT, activityClass, time);
            //load data
            list = loadActivity(NotsubmitActivity.this, activityClass, time);

            //Set adapter
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    };

    NotsubmitAdapter.NotsubmitItemListener editBTNListener = new NotsubmitAdapter.NotsubmitItemListener() {
        @Override
        public void myOnclick(int position, View view) {
            Toast.makeText(NotsubmitActivity.this, "edit ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notsubmit);

        appName = findViewById(R.id.tv_main_title_name);
        appName.setText("");


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        activityClass = (String)extras.get("activityClass");
        time = (String)extras.get("time");

        title = findViewById(R.id.tv_notsubmit_title);
        title.setText(activityClass);

        pullToRefresh = findViewById(R.id.refresh_notsubmit);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = loadActivity(NotsubmitActivity.this, activityClass, time);
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                pullToRefresh.setRefreshing(false);
            }
        });

        listView = findViewById(R.id.lv_notsubmit);

        //fab
        floatingBTN = findViewById(R.id.fab_notsubmit_add_activity);
        floatingBTN.setOnClickListener(this);

        //back button
        backBTN = findViewById(R.id.btn_notsubmit_back);
        backBTN.setOnClickListener(this);


        //use the activityClass and time passed from Fragment1 intent and load the list based on that
        list = loadActivity(NotsubmitActivity.this, activityClass, time);

        adapter = new NotsubmitAdapter(this, deleteBTNListener, editBTNListener);
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        if (view == backBTN) {
            finish();
        }

        else if (view == floatingBTN) {
            Intent intent = new Intent();
            intent.putExtra("activityClass", activityClass);
            intent.putExtra("time", time);
            intent.setClass(NotsubmitActivity.this, AddActivity.class);
            startActivity(intent);

        }
    }

}

