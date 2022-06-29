package com.ticket.ticket.utils;

public class TranslationUtitls {


    public static String getJsfsms(int i) {
        if (i == 1) {
            return "预支付";
        } else if (i == 2) {
            return "到货付";
        } else if (i == 3) {
            return "回单付";
        } else if (i == 4) {
            return "周期付";
        } else {
            return null;
        }
    }

    public static int getFkfslx(String str) {
        if ("现金支付".equals(str)) {
            return 1;
        } else if ("油卡支付".equals(str)) {
            return 2;
        } else if ("在线支付".equals(str)) {
            return 3;
        } else {
            return 0;
        }
    }

    public static Integer getSf(String str) {
        if ("是".equals(str)) {
            return 1;
        } else if ("否".equals(str)) {
            return 0;
        } else {
            return 0;
        }
    }

    public static int getJjfs(String str) {
        if ("重量".equals(str)) {
            return 1;
        } else if ("体积".equals(str)) {
            return 2;
        } else {
            return 0;
        }
    }

}
