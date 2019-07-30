package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianxing_li.expense.io.PhotoLoader;

public class EditActivity extends AppCompatActivity {
    private Button btnBack;
    private ImageView img;
    private TextView textView;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        textView = findViewById(R.id.tv_edit_pic_number);
        img = findViewById(R.id.img_edit_img);
        btnBack = findViewById(R.id.btn_edit_back);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = Integer.parseInt(String.valueOf(extras.get("position")));
        int total = Integer.parseInt(String.valueOf(extras.get("total")));
        String name = (String) extras.get("img_name");

        if (total==3) {
            textView.setText(position + 1 + "/" + (total));
        } else {
            textView.setText(position + 1 + "/" + (total-1));
        }

        img.setImageBitmap(PhotoLoader.loadImg(this, name));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
