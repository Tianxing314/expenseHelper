package com.tianxing_li.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tianxing_li.expense.ADT.ActivityClassADT;

import java.util.List;

public class Main1Adapter extends BaseAdapter {

    List<ActivityClassADT> list;
    LayoutInflater inflater;
    Button submitBTN;
    private Fragment1ItemListener submitBTNListener;

    public Main1Adapter(Context context, Fragment1ItemListener submitBTNListener) {

        this.inflater = LayoutInflater.from(context);
        this.submitBTNListener = submitBTNListener;
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

        View view = inflater.inflate(R.layout.tab1_item, null);

        submitBTN = view.findViewById(R.id.btn_main_tab1_submit);
        submitBTN.setTag(i);
        submitBTN.setOnClickListener(submitBTNListener);


        ImageView logo = view.findViewById(R.id.iv_main_tab1_logo);
        TextView activityName = view.findViewById(R.id.tv_main_tab1_activity_name);

        ActivityClassADT activityClassADT = list.get(i);
        //TODO: change to setImageBitmap and get real image logo
        logo.setImageResource(R.drawable.ic_account);
        activityName.setText((String) activityClassADT.getActivityClass());

        return view;
    }

    public static abstract class Fragment1ItemListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            myOnclick((Integer) view.getTag(), view);
        }
        public abstract void myOnclick(int position, View view);
    }
}
