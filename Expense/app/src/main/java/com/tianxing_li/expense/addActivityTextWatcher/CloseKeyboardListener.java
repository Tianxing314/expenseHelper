package com.tianxing_li.expense.addActivityTextWatcher;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CloseKeyboardListener implements TextWatcher {
    private EditText mThisEditText;
    private Activity activity;

    public CloseKeyboardListener(EditText editText, Activity activity) {
        this.mThisEditText = editText;
        this.activity = activity;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        if (str.contains("\r")||str.contains("\n")){
            mThisEditText.setText(str.replace("\r","").replace("\n",""));
            mThisEditText.clearFocus();
            InputMethodManager imm = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mThisEditText.getWindowToken(),0);
        }

    }
}
