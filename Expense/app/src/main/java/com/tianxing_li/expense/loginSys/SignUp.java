package com.tianxing_li.expense.loginSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp implements Formatter {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet result;

    public String signUp(String username, String password, String passwordVerify) {
        return null;
    }

    @Override
    public String isValidUsername(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return "It is not an email address.";
        }
        return null;
    }

    @Override
    public String isValidPassword(String password) {
        if (password.length() < 8) {
            return "Password must contain at least 6 characters.";
        } else if (password.length() > 12) {
            return "Password must contain no more than 12 characters.";
        }
        boolean isDigit = false;
        boolean isLetter = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(password.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        if (!isDigit) {
            return "Password must contain al least one digit.";
        }

        if (!isLetter) {
            return "Password must contain al least one letter.";
        }
        return null;
    }
}
