package com.tianxing_li.expense;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment2 extends Fragment {
    //private TextView textView;
    //private ListView listView;


    public DemoFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        //textView = view.findViewById(R.id.txt_display);
        //textView.setText(getArguments().getString("message"));

        ListView listView = view.findViewById(R.id.lv_main);
        //Myadapter

        //##################
        //2. create data source
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

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.ic_account);
        map.put("title", "YouTube");
        map.put("version", "version 2.4.1");
        map.put("size", "size: 84.24M");
        list.add(map);

        //MyAdapter
        //MyAdapter adapter = new MyAdapter(getActivity());
        Main2Adapter adapter = new Main2Adapter(getActivity());
        adapter.setList(list);
        //4. connect adapter to listview
        listView.setAdapter(adapter);
        //######################

        return view;
    }

}
