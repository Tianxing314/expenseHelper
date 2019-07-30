package com.tianxing_li.expense;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.tianxing_li.expense.adt.Photo;
import com.tianxing_li.expense.adt.PhotoList;

public class AddPhotoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private PhotoList photoList;
    private InItemOnClickListener addListener;
    private InItemOnClickListener editListener;
    private InItemOnClickListener deleteListener;

    public AddPhotoAdapter(Context context,
                           InItemOnClickListener addListener,
                           InItemOnClickListener editListener,
                           InItemOnClickListener deleteListener) {
        this.inflater = LayoutInflater.from(context);
        this.addListener = addListener;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        this.activity = (Activity) context;
    }

    public void setList(PhotoList photoList) { this.photoList = photoList; }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int i) {
        return photoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null) {
            view = inflater.inflate(R.layout.item_layout, null);

            holder = new ViewHolder();
            holder.img = view.findViewById(R.id.img_add_item);
            holder.btnDelete = view.findViewById(R.id.btn_add_delete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Photo photo = (Photo) getItem(i);
        if (photo.getStatus()==Photo.NEW) {
            holder.img.setImageBitmap(null);
            holder.img.setBackgroundResource(R.drawable.add_item);
            holder.img.setTag(i);
            holder.img.setOnClickListener(addListener);
            holder.btnDelete.setVisibility(View.INVISIBLE);
        } else {
            holder.img.setImageBitmap(photo.getBitmap());
            holder.img.setTag(i);
            holder.img.setOnClickListener(editListener);
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDelete.setTag(i);
            holder.btnDelete.setOnClickListener(deleteListener);
        }

        return view;
    }

    public static abstract class InItemOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnclick((Integer) v.getTag(), v);
        }

        public abstract void myOnclick(int position, View v);
    }

    private class ViewHolder {
        ImageView img;
        Button btnDelete;
    }
}
