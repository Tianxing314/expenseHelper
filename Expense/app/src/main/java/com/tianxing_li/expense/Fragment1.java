package com.tianxing_li.expense;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.adt.ActivityClassADT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import com.tianxing_li.expense.io.ActivityClassReader;
import com.tianxing_li.expense.io.ActivityClassWriter;
import com.tianxing_li.expense.io.ChangeState;


public class Fragment1 extends Fragment implements View.OnClickListener{

    FloatingActionButton floatingBTN;
    Main1Adapter adapter;
    List<ActivityClassADT> list;
    String inputActivityClassName;
    String time;//time that FAB is clicked
    ActivityClassADT activityClassADT;
    SwipeRefreshLayout pullToRefresh;

    Main1Adapter.Fragment1ItemListener submitBTNListener = new Main1Adapter.Fragment1ItemListener() {
        @Override
        public void myOnclick(final int position, View view) {

            PopupMenu popup = new PopupMenu(getContext(), view);
            popup.getMenuInflater().inflate(R.menu.fragment1_listview_menu,
                    popup.getMenu());
            popup.show();
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_fragment1_submit:
                            //TODO send the activityClass to web end when server and website are available
                            activityClassADT = list.get(position);
                            ChangeState.changeState(getActivity(), activityClassADT, "pending");
                            //load data
                            list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");

                            //Set adapter
                            adapter.setList(list);
                            adapter.notifyDataSetChanged();
                            break;

                        case R.id.menu_fragment1_delete:
                            Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return false;
                }
            });

            //##############
        }
    };

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        ListView listView = view.findViewById(R.id.lv_main_tab1);

        //pull down to refresh the page
        pullToRefresh = view.findViewById(R.id.refresh_fragment1);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");
                //Set adapter
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                pullToRefresh.setRefreshing(false);
            }
        });

        //fab
        floatingBTN = view.findViewById(R.id.fab_fragment1);
        floatingBTN.setOnClickListener(this);

        //load data
        list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");

        //Set adapter
        adapter = new Main1Adapter(getActivity(), submitBTNListener);
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //set listview item click listener
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String activityClass = list.get(i).getActivityClass();
                String time = list.get(i).getTime();
                Intent intent = new Intent();
                intent.putExtra("activityClass", activityClass);
                intent.putExtra("time", time);
                intent.setClass(getActivity(), NotsubmitActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == floatingBTN) {
            //get activityClass creating time in format hhmmss
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("hhmmss");
            time = format.format(calendar.getTime());
            Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();

            showDialog();
        }
    }

    //add activity class dialog
    private void showDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_activity_class_dialog, null);
        final EditText inputText = view.findViewById(R.id.et_fragment1_input);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        builder.setView(view);
        builder.setTitle("Create activity class");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                inputActivityClassName = inputText.getText().toString();
                activityClassADT = new ActivityClassADT(inputActivityClassName, "notsubmit", time);

                boolean noDuplicateName = ActivityClassWriter.saveActivityClass(getActivity(), activityClassADT);
                if (noDuplicateName) {
                    list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
                //almost never happen, but possible
                else {
                    Toast.makeText(getContext(), "duplicate name", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
