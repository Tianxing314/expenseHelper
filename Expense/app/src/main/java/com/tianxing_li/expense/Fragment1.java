package com.tianxing_li.expense;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tianxing_li.expense.ADT.ActivityADT;
import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment1 extends Fragment {

    private FloatingActionButton floatingBTN;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        ListView listView = view.findViewById(R.id.lv_main_tab1);

        //##### prep data ########
        List<ActivityClassADT> list = new ArrayList<>();

        ActivityClassADT activityClassADT = new ActivityClassADT("Beijing Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Shanghai Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Xinjiang Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Beijing Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Shanghai Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Xinjiang Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Beijing Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Shanghai Trip");
        list.add(activityClassADT);

        activityClassADT = new ActivityClassADT("Xinjiang Trip");
        list.add(activityClassADT);

        //##### end prep date ####

        //Create and set adapter
        Main1Adapter adapter = new Main1Adapter(getActivity());
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

        return view;
    }



}
