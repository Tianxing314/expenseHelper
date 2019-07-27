package com.tianxing_li.expense;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.ADT.ActivityClassADT;
import java.util.List;
import com.tianxing_li.expense.IO.ActivityClassReader;
import com.tianxing_li.expense.IO.ActivityClassWriter;



public class Fragment1 extends Fragment implements View.OnClickListener {

    FloatingActionButton floatingBTN;
    Main1Adapter adapter;
    List<ActivityClassADT> list;

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
            ActivityClassADT activityClassADT = new ActivityClassADT("Beijing Trip", "notsubmit");

            boolean noDuplicateName = ActivityClassWriter.saveActivityClass(getActivity(), activityClassADT);

            if (noDuplicateName) {
                list = ActivityClassReader.loadActivityClass(getActivity(), "notsubmit");
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }
            else {
                //TODO ask for rename
                Toast.makeText(getContext(), "duplicate name", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
