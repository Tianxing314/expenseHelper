package com.tianxing_li.expense;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment1 extends Fragment {
    //private TextView textView;
    //private ListView listView;


    public DemoFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        ListView listView = view.findViewById(R.id.lv_main);
        //ExpandableListView listView = view.findViewById(R.id.lv_main);

        //create data source
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logo", R.drawable.ic_account);
        map.put("title", "Gmail");
        map.put("version", "version 8.4.0");
        map.put("size", "size: 32.81M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.ic_account);
        map.put("title", "YouTube");
        map.put("version", "version 2.4.1");
        map.put("size", "size: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.ic_account);
        map.put("title", "Chrome");
        map.put("version", "version 6.2.0");
        map.put("size", "size: 15.15M");
        list.add(map);

        //Create and set adapter
        Main1Adapter adapter = new Main1Adapter(getActivity());
        adapter.setList(list);

        //Connect adapter to listview
        listView.setAdapter(adapter);

        return view;
    }

}
