package com.tianxing_li.expense;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Main2Adapter extends BaseAdapter {

    List<Map<String, Object>> list;
    LayoutInflater inflater;

    public Main2Adapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<Map<String, Object>> list) {
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

        View view = inflater.inflate(R.layout.item2, null);
        ImageView logo = view.findViewById(R.id.logo);
        TextView title = view.findViewById(R.id.title);
        TextView version = view.findViewById(R.id.version);
        TextView size = view.findViewById(R.id.size);

        Map map = list.get(i);

        logo.setImageResource((Integer) map.get("logo"));
        title.setText((String) map.get("title"));
        version.setText((String) map.get("version"));
        size.setText((String) map.get("size"));

        return view;
    }
}
