package com.tianxing_li.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.tianxing_li.expense.adt.ActivityADT;

public class NotsubmitAdapter extends BaseAdapter {

    List<ActivityADT> list;
    LayoutInflater inflater;

    public NotsubmitAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<ActivityADT> list) {
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

        View view = inflater.inflate(R.layout.notsubmit_item, null);
        ImageView type = view.findViewById(R.id.iv_notsubmit_type);
        TextView name = view.findViewById(R.id.tv_notsubmit_name);
        TextView amount = view.findViewById(R.id.tv_notsubmit_amount);
        TextView date = view.findViewById(R.id.tv_notsubmit_date);


        ActivityADT activityADT = list.get(i);
        //TODO: change to setImageBitmap and get real image logo
        String activityType = activityADT.getType();
        //set type image base on activity type
        switch (activityType) {
            case "Meal":
                type.setImageResource(R.drawable.ic_type_food);
                break;
            case "Transportation":
                type.setImageResource(R.drawable.ic_type_transportation);
                break;
            case "Accommodation":
                type.setImageResource(R.drawable.ic_type_accomodation);
                break;
            case "Others":
                type.setImageResource(R.drawable.ic_type_other);
                break;
        }
        name.setText((String) activityADT.getName());
        //TODO add currency sign
        amount.setText((String) activityADT.getAmount());
        date.setText((String) activityADT.getDate());

        return view;
    }
}
