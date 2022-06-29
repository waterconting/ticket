package com.ticket.ticket.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtils {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断目标字符串是否为空
     *
     * @param target
     * @return
     */
    public static Boolean isEmpty(String target) {
        if (target == null) {
            return true;
        }
        if (target.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断目标字符串是否不为空
     *
     * @param target
     * @return
     */
    public static Boolean isNotEmpty(String target) {
        if (target == null) {
            return false;
        }
        if (target.trim().length() <= 0) {
            return false;
        }
        return true;
    }

    //模糊查询时替换为空字段
    public static String nullReplace(String str) {
        if (isEmpty(str)) {
            str = "%%";
        } else {
            str = "%" + str + "%";
        }
        return str;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static String getzeoornumber(String number) {
        if ("".equals(number) || number == null) {
            return "0";
        } else {
            return number;
        }
    }

    /**
     * 如果为空则传递 空字符串
     *
     * @param str
     * @return
     */
    public static String getNullorString(String str) {

        if ("".equals(str) || str == null) {
            return "";
        } else {
            return str;
        }
    }
    public static String getNullorString3(Object str) {

        if (str == null) {
            return "";
        } else {
            if ("".equals(str)) {
                return "";
            }
            return str.toString();
        }
    }

    public static String getNullorString2(Object str) {

        if (str == null) {
            return null;
        } else {
            if ("".equals(str)) {
                return null;
            }
            return str.toString();
        }
    }


    public static double getMoneyDouble(Double d) {
        double value = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value;
    }

    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if (f.get(object) != null && !StringUtils.isEmpty(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 获取32位 UUID
     *
     * @return
     */
    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    /**
     * 获取64位 UUID
     *
     * @return
     */
    public static String getUUID64() {
        String uuid = UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    /**
     * 小写数字转换成 大写数字
     *
     * @param number 数字
     * @return
     */
    public static String getZWnumber(String number) throws Exception {
        int num = Integer.parseInt(number);
        return tranWan(num);

    }


    /**
     * 处理千万数字方法
     *
     * @param num
     * @return
     */
    private static String tranWan(int num) throws Exception {
        StringBuilder builder = new StringBuilder();
        if (num / 10000 > 0) {//说明
            builder.append(trans(num / 10000)).append("万");
        }
        int temp = num % 10000;
        if (temp > 0) {
            String trans = trans(temp);
            //首先判断是否有万位,
            if (builder.length() > 0) {

                //如果千位为0, 则需要补零
                if (temp / 1000 == 0) {
                    builder.append("零");
                }
            }
            builder.append(trans);

        }
        if (builder.length() == 0) {
            builder.append("零");
        }
        return builder.toString();
    }

    /**
     * 完成4位数转换
     *
     * @param num
     * @return
     */
    private static String trans(int num) throws Exception {
        String[] numeric = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuilder builder = new StringBuilder();
        builder.append(numeric[num / 1000] + "千").
                append(numeric[num / 100 % 10] + "百").
                append(numeric[num / 10 % 10] + "十").
                append(numeric[num % 10]);
        //去掉了零千....
        int index = -1;
        while ((index = builder.indexOf(numeric[0], index + 1)) != -1) {
            if (index < builder.length() - 1) {
                builder.deleteCharAt(index + 1);
            }
        }
        //去掉双零
        index = 0;
        while ((index = builder.indexOf("零零", index)) != -1) {
            builder.deleteCharAt(index);
        }

        if (builder.length() > 1) {
            //去掉开头的零
            if (builder.indexOf(numeric[0]) == 0) {
                builder.deleteCharAt(0);
            }
            //去掉末尾的零
            if (builder.indexOf(numeric[0]) == builder.length() - 1) {
                builder.deleteCharAt(builder.length() - 1);
            }

        }
        //把开头一十换成十
        if (builder.indexOf("一十") == 0) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isNotInteger(String str) {

        if (isInteger(str)) {
            return false;
        }
        return true;
    }

    public static String getnamelike(String name) {
        if (isEmpty(name)) {
            name = "";
        }
        name = "%" + name + "%";
        return name;
    }

    public static String getBeginLike(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return str + "%";
        }
    }

    public static List<Integer> getZtList(String str, int startI, int endI) {
        List<Integer> result = new ArrayList<>();
        if (StringUtils.isNotEmpty(str)) {
            result.add(Integer.valueOf(str));
        } else {
            for (int i = startI; i <= endI; i++) {
                result.add(i);
            }
        }
        return result;
    }

    public static String getJq(String str, String character, int lx) {
        int jqws = str.indexOf(character);//截取位数
        if (jqws > 0) {
            String str1 = "";
            if (lx == 1) {
                str1 = str.substring(0, jqws + 1);
            } else {
                str1 = str.substring(jqws + 1, str.length());
            }
            return str1;
        } else {
            return str;
        }
    }


    public static Date getDateValue(Object ob) {
        if (ob == null) {
            return null;
        }
        try {
            return (Date) ob;
        } catch (Exception e) {
            try {
                return format.parse(ob.toString().trim());
            } catch (ParseException ex) {
                try {
                    return formatDay.parse(ob.toString().trim());
                } catch (ParseException exc) {
                    return null;
                }
            }
        }
    }

    public static String getStringValue(Object ob) {
        if (ob == null) {
            return null;
        }
        return ob.toString().trim();
    }

    public static Integer getIntegerValue(Object ob) {
        if (ob == null) {
            return 0;
        }
        try {
            return (Integer) ob;
        } catch (Exception e) {
            return Integer.valueOf(ob.toString().trim());
        }
    }

    public static BigDecimal getBigDecimalValue(Object ob) {
        if (ob == null) {
            return new BigDecimal(0);
        }
        try {
            return (BigDecimal) ob;
        } catch (Exception e) {
            try {
                return new BigDecimal(ob.toString().trim());
            } catch (Exception s) {
                return null;
            }
        }
    }

    /*去掉字符串中的前后空格*/
    public static String removeSpaces(String content) {
        if (isNotEmpty(content)) {
            return content.trim();
        } else {
            return content;
        }
    }

    //数字转字母 1-26 ： A-Z
    public static String numberToLetter(int num) {
        if (num <= 0) {
            return null;
        }
        String letter = "";
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter = ((char) (num % 26 + (int) 'A')) + letter;
            num = (int) ((num - num % 26) / 26);
        } while (num > 0);

        return letter;
    }

    //判断是否是数字
    public static boolean isNumber(String str) {
        Boolean strResult = str.matches("-?[0-9]+.?[0-9]*");
        return strResult;
    }


    /*去掉字符串首尾*/
    public static String interceptStr(String str) {
        StringBuilder strB = new StringBuilder(str);
        strB = strB.deleteCharAt(0);
        StringBuilder resutl = strB.deleteCharAt(strB.length() - 1);
        return resutl.toString();
    }
}
