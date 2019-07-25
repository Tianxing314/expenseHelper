package com.tianxing_li.expense;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.util.ArrayList;
import java.util.List;


public class Fragment3 extends Fragment {

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3_layout, container, false);

        ListView listView = view.findViewById(R.id.lv_main_tab3);

        //prep data
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

        //Create and set adapter
        Main3Adapter adapter = new Main3Adapter(getActivity());
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

        return view;
    }

}
