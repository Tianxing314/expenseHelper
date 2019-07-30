package com.tianxing_li.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianxing_li.expense.adt.ActivityClassADT;

import java.util.List;

public class Main2Adapter extends BaseAdapter {

    List<ActivityClassADT> list;
    LayoutInflater inflater;

    public Main2Adapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<ActivityClassADT> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(R.layout.tab2_item, null);
        ImageView logo = view.findViewById(R.id.iv_main_tab2_logo);
        TextView activityName = view.findViewById(R.id.tv_main_tab2_activity_name);


        ActivityClassADT activityClassADT = list.get(i);
        //TODO: change to setImageBitmap and get real image logo
        logo.setImageResource(R.drawable.ic_folder);
        activityName.setText((String) activityClassADT.getActivityClass());

        return view;
    }
}
