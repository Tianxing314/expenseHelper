package com.tianxing_li.expense.loginSys;

/**
 * validate he format of inputs
 * (does not check information related to db)
 */
public interface Formatter {
    String isValidUsername(String username);
    String isValidPassword(String password);
}
