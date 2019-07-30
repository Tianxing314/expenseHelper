package com.tianxing_li.expense.addActivityTextWatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class JumpListener implements TextWatcher {
    private EditText mThisView;
    private View mNextView;

    public JumpListener(EditText mThisView, View mNextView) {
        super();
        this.mThisView = mThisView;
        if (mNextView != null) {
            this.mNextView = mNextView;
        }
    }

    // 输入文本之前的状态
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {
    }

    // 输入文字中的状态，count是一次性输入字符数
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    // 输入文字后的状态
    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        if (str.contains("\r")||str.contains("\n")){
            mThisView.setText(str.replace("\r","").replace("\n",""));
            if (mNextView!=null){
                mNextView.requestFocus();
                if (mNextView instanceof EditText){
                    // instanceof 是判断其左边对象是否为其右边类的实例
                    EditText et = (EditText) mNextView;
                    et.setSelection(et.getText().length());
                }
            }
        }
    }
}
