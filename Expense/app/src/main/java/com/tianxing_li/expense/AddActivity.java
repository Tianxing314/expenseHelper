package com.tianxing_li.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tianxing_li.expense.addActivityTextWatcher.CloseKeyboardListener;
import com.tianxing_li.expense.addActivityTextWatcher.DoubleDecimalListener;
import com.tianxing_li.expense.addActivityTextWatcher.JumpListener;
import com.tianxing_li.expense.adt.ActivityADT;
import com.tianxing_li.expense.adt.Photo;
import com.tianxing_li.expense.adt.PhotoList;
import com.tianxing_li.expense.io.ActivityWriter;
import com.tianxing_li.expense.io.PhotoLoader;
import com.tianxing_li.expense.io.PhotoSaver;
import com.tianxing_li.expense.uri2bitmap.ImgConverter;

import java.io.File;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    private final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private Selection[] selections;

    private Button btnBack;
    private Button btnAdd;

    private Button btnTest;
    private ImageView img_test;
    private TextView tv_test;

    private TextView tvDate;
    private TextView tvCommentCount;

    //private EditText etType;
    private EditText etName;
    private EditText etAmount;
    private EditText etComment;

    //private ListView lvPhotos;
    private GridView gvPhotos;
    private PhotoList photoList;
    private AddPhotoAdapter addPhotoAdapter;

    //intent extras from NotSubmitActivity
    private String activityClass;
    private String time;

    private AddPhotoAdapter.InItemOnClickListener addListener = new AddPhotoAdapter.InItemOnClickListener() {
        @Override
        public void myOnclick(int position, View v) {
            openAddDialog();
        }
    };

    private void openAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a source");
        builder.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it;
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show pop up for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                } else {
                    //system os is less than max
                    pickImageFromGallery();
                }
            }
        });
        builder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it;
                        String[] permissions = {Manifest.permission.CAMERA};
                        //show pop up for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission already granted
                    takeAPhoto();
                    }
                } else {
                    //system os is less than max
                    takeAPhoto();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void takeAPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(this.getDataDir().getAbsolutePath()+"/img/", "temp.png"));
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private AddPhotoAdapter.InItemOnClickListener editListener = new AddPhotoAdapter.InItemOnClickListener() {
        @Override
        public void myOnclick(int position, View v) {
            Intent intent = new Intent();
            intent.putExtra("position", ""+v.getTag());
            intent.putExtra("img_name", photoList.get((Integer) v.getTag()).getPhotoName());
            intent.putExtra("total", ""+photoList.size());
            intent.setClass(AddActivity.this, EditActivity.class);
            startActivity(intent);
        }
    };

    private AddPhotoAdapter.InItemOnClickListener deleteListener = new AddPhotoAdapter.InItemOnClickListener() {
        @Override
        public void myOnclick(int position, View v) {
            photoList.remove((Integer) v.getTag());
            addPhotoAdapter.notifyDataSetChanged();
        }
    };


//    private Bitmap bitmap;

    private void select(int i) {
        for (Selection s: selections) {
            if (s.id==i) {
                s.select();
            } else {
                s.unSelect();
            }
        }
    }

//    private void unSelectAll() {
//        for (Selection s: selections)
//            s.unSelect();
//    }

    class Selection {
        int id;
        RelativeLayout exterior;
        TextView info;
        boolean isSelected;

        Selection(int id, RelativeLayout exterior, TextView info) {
            this.id = id;
            this.exterior = exterior; this.info=info;
            isSelected=false;
        }

        void select() {
            this.isSelected=true;
            this.exterior.setBackground(getDrawable(R.drawable.layout_selected_border));
            this.info.setTextColor(Color.parseColor("#e3551e"));
        }

        void unSelect() {
            this.isSelected=false;
            this.exterior.setBackground(getDrawable(R.drawable.layout_border));
            this.info.setTextColor(Color.parseColor("#666666"));
        }

        void setOnClickListener(View.OnClickListener listener) {
            this.exterior.setOnClickListener(listener);
        }
    }

//    private class TypeListener extends JumpListener {
//
//        public TypeListener(EditText mThisView, View mNextView) {
//            super(mThisView, mNextView);
//        }
//        // 输入文字后的状态
//        @Override
//        public void afterTextChanged(Editable editable) {
//            super.afterTextChanged(editable);
//            unSelectAll();
//        }
//    }

    private class CommentListener extends CloseKeyboardListener {
        private CharSequence temp; // 监听前的文本
        private int editStart; // 光标开始位置
        private int editEnd; // 光标结束位置
        public CommentListener(EditText editText, Activity activity) {
            super(editText, activity);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            temp = s;
        }

        // 输入文字中的状态，count是一次性输入字符数
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//          if (charMaxNum - s.length() <= 5) {
//              tip.setText(还能输入 + (charMaxNum - s.length()) + 字符);
//          }
            tvCommentCount.setText((s.length()) + "/" + 100);
        }

        // 输入文字后的状态
        @Override
        public void afterTextChanged(Editable s) {
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
            super.afterTextChanged(s);
            editStart = etComment.getSelectionStart();
            editEnd = etComment.getSelectionEnd();
            if (temp.length() > 100) {
//              Toast.makeText(getApplicationContext(), 最多输入10个字符, Toast.LENGTH_SHORT).show();
                s.delete(editStart - 1, editEnd);
                etComment.setText(s);
                etComment.setSelection(s.length());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        activityClass = (String)extras.get("activityClass");
        time = (String)extras.get("time");

        Log.i("Sky", "Addactivity Oncreate " + activityClass + time);

        init();
    }

    private void init() {

        btnAdd = findViewById(R.id.btn_add_submit);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = etName.getText().toString();
                String type = "Others";
                for (Selection s:selections) {
                    if (s.isSelected)
                        type = s.info.getText().toString();
                }
                String amount = etAmount.getText().toString();
                String date = tvDate.getText().toString();
                String comment = etComment.getText().toString();
                String[] image = {"", "", ""};
                for (int i=0; i<photoList.size(); i++) {
                    if (photoList.get(i)!=null)
                        image[i] = photoList.get(i).getPhotoName();
                }

                ActivityADT activityADT = new ActivityADT(name, type, amount, date, comment, image);
                Log.i("Sky", "AddActivity " +activityClass + time);

                ActivityWriter.saveActivity(AddActivity.this, activityADT, activityClass, time);
                finish();
            }
        } );

        gvPhotos = findViewById(R.id.gv_add_photo);
        photoList = new PhotoList();

        addPhotoAdapter = new AddPhotoAdapter(this, addListener, editListener, deleteListener);
        addPhotoAdapter.setList(photoList);

        gvPhotos.setAdapter(addPhotoAdapter);

        btnBack = findViewById(R.id.btn_add_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnTest = findViewById(R.id.btn_add_test);
        img_test = findViewById(R.id.img_add_test);
        tv_test = findViewById(R.id.tv_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dataDir = AddActivity.this.getDataDir();
                File imgDir = new File(dataDir.getAbsolutePath() + "/img/");
                File file = new File(imgDir, "a.jpg");
                String path = file.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                img_test.setImageBitmap(bitmap);
            }
        });

        tvCommentCount = findViewById(R.id.tv_add_comment_count);

        etName = findViewById(R.id.et_add_activity);
        //etType = findViewById(R.id.et_add_type);
        etAmount = findViewById(R.id.et_add_amount);
        etComment = findViewById(R.id.et_add_comment);

        //etType.addTextChangedListener(new TypeListener(etType, etName));
        etName.addTextChangedListener(new JumpListener(etName, etAmount));
        etAmount.addTextChangedListener(new DoubleDecimalListener());
        etComment.addTextChangedListener(new CommentListener(etComment, this));

        tvDate = findViewById(R.id.tv_add_select_date);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickDateDialog();
            }
        });
        RelativeLayout relativeLayoutType1 = findViewById(R.id.rl_add_type_1);
        TextView textViewType1 = findViewById(R.id.tv_add_type1);
        Selection selection1 = new Selection(1, relativeLayoutType1, textViewType1);

        RelativeLayout relativeLayoutType2 = findViewById(R.id.rl_add_type_2);
        TextView textViewType2 = findViewById(R.id.tv_add_type2);
        Selection selection2 = new Selection(2, relativeLayoutType2, textViewType2);

        RelativeLayout relativeLayoutType3 = findViewById(R.id.rl_add_type_3);
        TextView textViewType3 = findViewById(R.id.tv_add_type3);
        Selection selection3 = new Selection(3, relativeLayoutType3, textViewType3);

        RelativeLayout relativeLayoutType4 = findViewById(R.id.rl_add_type_4);
        TextView textViewType4 = findViewById(R.id.tv_add_type4);
        Selection selection4 = new Selection(4, relativeLayoutType4, textViewType4);

        selections = new Selection[]{selection1, selection2, selection3, selection4};

        selection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddActivity.this, "pressed on type 1", Toast.LENGTH_SHORT).show();
                select(1);
            }
        });

        selection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddActivity.this, "pressed on type 2", Toast.LENGTH_SHORT).show();
                select(2);
            }
        });

        selection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddActivity.this, "pressed on type 3", Toast.LENGTH_SHORT).show();
                select(3);
            }
        });

        selection4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddActivity.this, "pressed on type 4", Toast.LENGTH_SHORT).show();
                select(4);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showPickDateDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_date, null);
        final DatePicker startTime = view.findViewById(R.id.st);
        startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Select date");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int month = startTime.getMonth() + 1;
                String st = "" + startTime.getYear() + "/" + month+ "/" + startTime.getDayOfMonth();
                tvDate.setText(st);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //********* Lose EditText focus and close keyboard ********* BEGIN
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE ) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            v.clearFocus();
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //****end

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap bitmap = BitmapFactory.decodeFile(this.getDataDir().getAbsolutePath()+"/img/temp.png");
//            String imgName = System.currentTimeMillis()+".png";
//            PhotoSaver.saveImg(this, imgName, bitmap);


            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String imgName = System.currentTimeMillis()+".png";
            PhotoSaver.saveImg(this, imgName, imageBitmap);
            Photo p = new Photo();
            p.setPhotoName(imgName);
            p.setBitmap(imageBitmap);
            p.setAssigned();
            photoList.add(p);
            addPhotoAdapter.notifyDataSetChanged();
            //像素过低
        } else if (resultCode==RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = ImgConverter.getBitmapFormUri(this, uri);
                String imgName = System.currentTimeMillis()+".png";
                //String imgName = "a.jpg";
                PhotoSaver.saveImg(this, imgName, bitmap);
                Photo p = new Photo();
                p.setPhotoName(imgName);
                bitmap = PhotoLoader.loadImg(this, imgName);
                Log.i("wd4", uri.toString());
                p.setBitmap(bitmap);
                p.setAssigned();
                photoList.add(p);
                addPhotoAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
