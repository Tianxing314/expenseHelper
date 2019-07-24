package com.tianxing_li.expense.loginSys;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Login implements Formatter {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet result;

    public String login(String username, String password) {
        String resultString=null;
        if (isValidUsername(username) != null)
            return isValidUsername(username);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/expense?autoReconnect=true&useSSL=false",
                    "root",
                    "password");
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail to connect to server.";
        }
        try {
            pst = conn.prepareStatement(
                    "select password from users where email = '"+username+"'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something goes wrong. Try again later.";
        }
        try {
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something goes wrong. Try again later.";
        }

        try {
            if (!result.next())
                    resultString = "Email does not exist.";
                else {
                    String correctPassword = result.getString(1);
                    if (correctPassword.equals(Encrypt.encrypt(password)))
                        resultString = "Success!";
                    else
                        resultString = "Incorrect Password.";
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something goes wrong. Try again later.";
        }

        try {
            conn.close();
            pst.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    public String isValidUsername(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return "It is not an email address.";
        }
        return null;
    }

//    @Override
//    public String isValidPassword(String password) {
//        if (password.length() < 8) {
//            return "Password must contain at least 6 characters.";
//        } else if (password.length() > 12) {
//            return "Password must contain no more than 12 characters.";
//        }
//        boolean isDigit = false;
//        boolean isLetter = false;
//
//        for (int i = 0; i < password.length(); i++) {
//            if (Character.isDigit(password.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
//                isDigit = true;
//            } else if (Character.isLetter(password.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
//                isLetter = true;
//            }
//        }
//        if (!isDigit) {
//            return "Password must contain al least one digit.";
//        }
//
//        if (!isLetter) {
//            return "Password must contain al least one letter.";
//        }
//        return null;
//    }
    @Override
    public String isValidPassword(String password) {
        return null;
    }
}
