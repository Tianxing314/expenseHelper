package com.tianxing_li.expense.addActivityTextWatcher;

import android.text.Editable;
import android.text.TextWatcher;

public class DoubleDecimalListener implements TextWatcher {
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    //主要是重置文本改变事件,判断当前输入的内容
    public void afterTextChanged(Editable edt)
    {
        String temp = edt.toString();
        int posDot = temp.indexOf(".");
        if (posDot <= 0) return;
        if (temp.length() - posDot - 1 > 2)
        {
            edt.delete(posDot + 3, posDot + 4);
        }
    }
}
