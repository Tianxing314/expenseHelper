package com.tianxing_li.expense;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.tianxing_li.expense.adt.ActivityADT;
import com.tianxing_li.expense.io.PhotoLoader;
import com.tianxing_li.expense.io.SettingsReader;

public class NotsubmitAdapter extends BaseAdapter {

    List<ActivityADT> list;
    LayoutInflater inflater;
    Context context;
    Button deleteBTN;
    Button editBTN;

    private NotsubmitItemListener deleteBTNListener;

    private NotsubmitItemListener editBTNListener;

    public NotsubmitAdapter(Context context, NotsubmitItemListener deleteBTNListener, NotsubmitItemListener editBTNListener) {

        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.deleteBTNListener = deleteBTNListener;
        this.editBTNListener = editBTNListener;

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
        TextView currency = view.findViewById(R.id.tv_notsubmit_currency);
        TextView amount = view.findViewById(R.id.tv_notsubmit_amount);
        TextView date = view.findViewById(R.id.tv_notsubmit_date);

        deleteBTN = view.findViewById(R.id.btn_notsubmit_delete);
        deleteBTN.setTag(i);
        deleteBTN.setOnClickListener(deleteBTNListener);

        editBTN = view.findViewById(R.id.btn_notsubmit_edit);
        editBTN.setTag(i);
        editBTN.setOnClickListener(editBTNListener);


        TextView comment = view.findViewById(R.id.tv_notsubmit_comment);
        ImageView img1 = view.findViewById(R.id.iv_notsubmit_img1);
        ImageView img2 = view.findViewById(R.id.iv_notsubmit_img2);
        ImageView img3 = view.findViewById(R.id.iv_notsubmit_img3);


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
            case "Other":
                type.setImageResource(R.drawable.ic_type_other);
                break;
        }
        name.setText(activityADT.getName());
        //set currency sign
        SettingsReader settingsReader = new SettingsReader((Activity)context);
        String settedCurrency = settingsReader.getCurrencySetting();
        if (settedCurrency.equals("CNY¥")) {
            currency.setText("¥");
        }
        else {
            currency.setText("$");
        }


        amount.setText(activityADT.getAmount());
        date.setText(activityADT.getDate());

        //#############
        comment.setText(activityADT.getComment());
        String[] image = activityADT.getImage();
        String image1 = image[0];
        String image2 = image[1];
        String image3 = image[2];

        Log.i("bbb", image1 + " 1");
        Log.i("ccc", image1);

        if (image1.equals("no_image")) {
            Log.i("ccc", "here");
            img1.getLayoutParams().height = 0;
            img1.getLayoutParams().width = 0;
            img2.getLayoutParams().height = 0;
            img2.getLayoutParams().width = 0;
            img3.getLayoutParams().height = 0;
            img3.getLayoutParams().width = 0;
        } else {
            img1.setImageBitmap(PhotoLoader.loadImg((Activity) context, image1));
            if (!image2.equals("no_image")) {
                img2.setImageBitmap(PhotoLoader.loadImg((Activity) context, image2));
            }
            if (!image3.equals("no_image")) {
                img3.setImageBitmap(PhotoLoader.loadImg((Activity) context, image3));
            }
        }

        return view;
    }

    public static abstract class NotsubmitItemListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            myOnclick((Integer) view.getTag(), view);
        }
        public abstract void myOnclick(int position, View view);
    }
}
