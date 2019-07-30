package com.tianxing_li.expense;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.tianxing_li.expense.adt.ActivityClassADT;
import java.util.List;
import com.tianxing_li.expense.io.ActivityClassReader;



public class Fragment3 extends Fragment implements View.OnClickListener {

    Main3Adapter adapter;
    List<ActivityClassADT> list;
    SwipeRefreshLayout pullToRefresh;

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        ListView listView = view.findViewById(R.id.lv_main_tab3);

        //pull down to refresh page
        pullToRefresh = view.findViewById(R.id.refresh_fragment3);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = ActivityClassReader.loadActivityClass(getActivity(), "finished");
                //Set adapter
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                pullToRefresh.setRefreshing(false);
            }
        });

        //load data
        list = ActivityClassReader.loadActivityClass(getActivity(), "finished");

        //Set adapter
        adapter = new Main3Adapter(getActivity());
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        //Connect adapter to listview
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
