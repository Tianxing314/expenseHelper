package com.tianxing_li.expense.loginSys;

import java.security.MessageDigest;

public class Encrypt {

    private static final String salt = "!!@##!!$@&";

    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr + salt;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(encrypt("Ww772438058"));
    }
}
