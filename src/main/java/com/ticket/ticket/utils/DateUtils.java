package com.ticket.ticket.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 */
public class DateUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sdfday4 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat monthformat = new SimpleDateFormat("yyyy年MM月");
    public static final SimpleDateFormat sdfday5 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat sdfday6 = new SimpleDateFormat("yyyyMMdd");


    /**
     *  专用
     * @param str
     * @param sdf
     * @return
     */
    public static Date getNullOrDate(Object str, SimpleDateFormat sdf){

         if(str==null){
             return null;
         }
        if (StringUtils.isEmpty(str.toString())) {
            return null;
        }
        if(sdf==null){
            sdf=dayformat;
        }
        try {
            return sdf.parse(str.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 专用
     *
     * @param date
     * @return
     */
    public static String getDasteString(Date date) {
        if (date == null) {
            return null;
        }
        return sdfday5.format(date);
    }

    /**
     * 专用
     *
     * @param date
     * @return  yyyymmdd
     */
    public static String getDateStringTwo(Date date) {
        if (date == null) {
            return null;
        }
        return sdfday6.format(date);
    }

    public static Date getDateFromString(String str, SimpleDateFormat sdf) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateStringBySdf(Date date, SimpleDateFormat sdf) {
        if (date == null) {
          return null;
        }
        if (sdf == null) {
            sdf = format;
        }
        return sdf.format(date);
    }

    /**
     * 获取占用天数
     * @param nowTime 当前时间
     * @param StarteTime  有效截止时间
     * @return
     */
    public static int getZyTs(Date nowTime, Date StarteTime){
        if(nowTime==null){
            return 0;
        }
        if(StarteTime==null) {
            return 0;
        }
        System.out.println("当前时间："+nowTime.getTime());
        System.out.println("目标时间："+StarteTime.getTime());
        int days = (int) ((nowTime.getTime() - StarteTime.getTime()) / (1000*3600*24));

        return days;
    }
    /**
     * 获取给定时间前几小时的时间
     *
     * @param date 时间段的结束时间
     * @param hour 小时
     * @return String
     */
    public static String getbeginString(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, hour);// before  hour
        return format.format(cal.getTime());
    }

    /**
     * 给传入的时间增加输入的月份
     *
     * @param old   传入时间
     * @param month 增加月份数量
     * @return
     */
    public static Date getNewDate(Date old, Integer month) {
        if (old == null || old.before(new Date()))
            old = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(old);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取给定时间前几小时的时间
     *
     * @param date 时间段的结束时间
     * @param hour 小时
     * @return Date
     */
    public static Date getbeginDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, -hour);// before  hour
        return cal.getTime();
    }

    /**
     * 获取给定时间后几小时的时间
     *
     * @param date 时间段的结束时间
     * @param hour 小时
     * @return Date
     */
    public static Date getendDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, hour);// before  hour
        return cal.getTime();
    }

    public static String getnamelike(String str) {
        if (StringUtils.isEmpty(str))
            return "%%";
        return "%" + str + "%";
    }

    /**
     * 获取一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        if(date==null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一天结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 比较所给时间与当前时间差是否小于给定分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static boolean datecompare(Date date, int minute) {
        if (date == null)
            return false;
        int dev = (int) ((date.getTime() - System.currentTimeMillis()) / (60 * 1000));
        return dev < minute && dev > 0;
    }

    /**
     * 判定  时间为空
     */

    public static boolean isNull(Date data) {
        if (data == null) {
            return true;
        }
        return false;
    }

    /**
     * 判定  时间不为空
     */
    public static boolean isNotNull(Date data) {
        if (data == null) {
            return false;
        }
        return true;
    }

    /**
     * 给指定时间 加一个月
     *
     * @param date 月份
     * @return 下一个月份
     */
    public static Date AddOneMenth(Date date) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, renewalsdata);
        return calendar.getTime();
    }

    //获取昨天
    public static Date getYesterday(Date date) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
        return calendar.getTime();
    }

    //获取明天
    public static Date getTomorrow(Date date) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, 1); //设置为后一天
        return calendar.getTime();
    }

    //时间加几天
    public static Date addDay(Date date,int ts) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, ts); //设置为后一天
        return calendar.getTime();
    }

    //时间加几小时
    public static Date AddHours(Date date, int hour) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.HOUR, hour); //24小时制
        return calendar.getTime();
    }

    //时间加几分钟
    public static Date AddMin(Date date, int min) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.MINUTE, min); //59分钟
        return calendar.getTime();
    }

    //时间减几分钟
    public static Date subtractMin(Date date, int min) {
        if (date == null) {
            return null;
        }
        int renewalsdata = 1;
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.MINUTE, -min); //59分钟
        return calendar.getTime();
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        if (dateDate == null) {
            return null;
        }
        return format.format(dateDate);
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToDayStrLong(Date dateDate) {
        if (dateDate == null) {
            return null;
        }
        return dayformat.format(dateDate);
    }

    //获取周一的时间
    public static Date getMonday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        return mondayDate;
    }

    //获取本月1号的时间
    public static Date getMonthOne(Date date) {
        //规定返回日期格式
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
//设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return gcLast.getTime();
    }


    //获取本月月份
    public static String getMonth(Date date) {

        String month = null;
        if (date == null) {
            return null;
        }
        month = monthformat.format(date);
        return month;
    }


    public static String getStringByDateForDay4(Date data) {
        String time = null;
        if (data == null) {
            return null;
        }
        time = sdfday4.format(data);
        return time;
    }


    /**
     * 比较所给时间与当前时间差是否小于给定分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static boolean datecompare2(Date date, int minute) {
        if (date == null)
            return false;
        int dev = Math.abs((int) ((date.getTime() - System.currentTimeMillis()) / (60 * 1000)));
        return dev < minute;
    }

    /*    *//**
     * @Description: 任意时间字符串转换成时间，无需指定解析模板
     * @param: 日期
     * @return:
     * @Author: Neo
     * @Date: 2018/11/9 9:50
     * @Version: 1.0
     *//*
    public static Date parseStringToDate(String date) throws ParseException {
        Date result;
        String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format = new SimpleDateFormat(parse);
        result = format.parse(date);
        return result;
    }*/

    /**
     * @Description: 任意时间字符串转换成时间，无需指定解析模板
     * @param: 日期
     * @return:
     * @Author: Neo
     * @Date: 2018/11/9 9:50
     * @Version: 1.0
     */
    /*public static String parseStringToDate(String date) throws ParseException {
        String result;
        String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        SimpleDateFormat format = new SimpleDateFormat(parse);
        result = format.format(date);
        return result;
    }*/

    /**
     * @Description: 任意时间字符串转换成时间，无需指定解析模板
     * @param: 日期
     * @return:
     * @Author: Neo
     * @Date: 2018/11/9 9:50
     * @Version: 1.0
     */
    public static Date parseStringToDate(String date)  {
        Date result;
        String parse = date.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        SimpleDateFormat format = new SimpleDateFormat(parse);
        try {
            result = format.parse(date);
            return result;
        }catch (Exception e){
            System.out.println("任意时间字符串转换成时间失败：");
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) { //不同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {//同一年
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }


    }

    /**
     *  获取给定时间的月份数字-0代表一月
     * @param date
     * @return
     */
    public static int getMonth1(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month;
    }

    /**
     *  获取给定时间的年数字
     * @param date
     * @return
     */
    public static int getYear1(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 给传入的时间增加输入的月份
     * @param date    传入时间
     * @param number 增加月份数量
     * @return
     */
    public static String  getAddMonth(Date date, Integer number) {
        SimpleDateFormat formatMonth2 = new SimpleDateFormat("yyyy-MM");
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        int year= getYear1(date);
        int  month=getMonth1(date) ;
        ca.set(year, month+1, 0);// 月份是从0开始的，所以11表示12月
        ca.add(Calendar.YEAR,0);
        ca.add(Calendar.MONTH, number);// 月份减
        ca.add(Calendar.DATE,0);//天
        Date resultDate = ca.getTime(); // 结果
        return  formatMonth2.format(resultDate);
    }
    /**
     * 给传入的时间增加输入的月份
     * @param date    传入时间
     * @param number 增加月份数量
     * @return
     */
    public static Date  getAddMonthForDate(Date date, Integer number) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        int year= getYear1(date);
        int  month=getMonth1(date) ;
        ca.set(year, month+1, 0);// 月份是从0开始的，所以11表示12月
        ca.add(Calendar.YEAR,0);
        ca.add(Calendar.MONTH, number);// 月份减
        ca.add(Calendar.DATE,0);//天
        return  ca.getTime();
    }

    /**
     * 给传入的时间增加输入的年数
     * @param date    传入时间
     * @param number 增加年数量
     * @return
     */
    public static String  getAddYear(Date date, Integer number) {
        SimpleDateFormat formatMonth2 = new SimpleDateFormat("yyyy-MM");
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        int year= getYear1(date);
        int  month=getMonth1(date) ;
        ca.set(year, month+1, 0);// 月份是从0开始的，所以11表示12月
        ca.add(Calendar.YEAR,number); //年加减
        ca.add(Calendar.MONTH, 0);// 月份
        ca.add(Calendar.DATE,0);//天
        Date resultDate = ca.getTime(); // 结果
        return  formatMonth2.format(resultDate);
    }
    /**
     * 给传入的时间增加输入的年数
     * @param date    传入时间
     * @param number 增加年数量
     * @return
     */
    public static Date  getAddYeaForDate(Date date, Integer number) {
        SimpleDateFormat formatMonth2 = new SimpleDateFormat("yyyy-MM");
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        int year= getYear1(date);
        int  month=getMonth1(date) ;
        ca.set(year, month+1, 0);// 月份是从0开始的，所以11表示12月
        ca.add(Calendar.YEAR,year); //年加减
        ca.add(Calendar.MONTH, 0);// 月份
        ca.add(Calendar.DATE,0);//天
        return  ca.getTime();
    }

    /**
     * 给传入的时间增加、减少任意秒数
     * @param date    传入时间
     * @param number 增加年数量
     * @return
     */
    public static Date  getAddSecond(Date date, Integer number) {
        Calendar newTime= Calendar.getInstance();
        newTime.setTime(date);
        newTime.add(Calendar.SECOND, number);//日期加10秒
        return newTime.getTime();
    }

    /**
     * 转换10位
     *
     * @param strDate
     * @return
     */
    public static Date stringToDate18(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition poss = new ParsePosition(0);
        Date dat = simpleDateFormat.parse(strDate, poss);
        return dat;
    }

    /**
     * date转String 18
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr18(Date dateDate) {
        if (isNull(dateDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
