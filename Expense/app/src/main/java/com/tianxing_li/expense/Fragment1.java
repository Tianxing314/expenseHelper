package com.tianxing_li.expense;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import com.tianxing_li.expense.IO.ActivityClassReader;
import com.tianxing_li.expense.IO.ActivityClassWriter;



public class Fragment1 extends Fragment implements View.OnClickListener {

    FloatingActionButton floatingBTN;
    Main1Adapter adapter;
    List<ActivityClassADT> list;
    String inputActivityClassName;
    String time;//time that FAB is clicked
    ActivityClassADT activityClassADT;

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        ListView listView = view.findViewById(R.id.lv_main_tab1);

        //check if first time open the app
        floatingBTN = view.findViewById(R.id.fab_fragment1);
        floatingBTN.setOnClickListener(this);

        //load data
        list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");

        //Set adapter
        adapter = new Main1Adapter(getActivity());
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

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
                    //TODO ask for rename
                    Toast.makeText(getContext(), "duplicate name", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
