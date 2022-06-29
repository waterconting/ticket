package com.ticket.ticket.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Random;

/***
 * 数字操作工具类
 */
public class NumeralUtils {

    /**
     *   获取指定范围里的随机数-正数
     * @param max  最大值
     * @param min 最小值
     * @return
     */
    public  static  Integer getReadom(int max ,int min ){
        Random random = new Random();
        Integer s = random.nextInt(max)%(max-min+1) + min;
        return  s;
    }

    /**
     *   获取指定范围里的随机数-负数
     * @param max  最大值
     * @param min 最小值
     * @return
     */
    public static Integer getNegativeReadom(int max ,int min ){
        Random random = new Random();
        Integer s =0- (random.nextInt(max)%(max-min+1) + min);
        return  s;
    }

    /**
     *   获取指定范围里的随机数-正负数
     * @param max  最大值
     * @param min 最小值
     * @return
     */
    public static Integer getAllReadom(int max ,int min ){
        Random random = new Random();
        Integer s=0;
        int flag = random.nextInt(1);
        if(flag==1){
            s =random.nextInt(max)%(max-min+1) + min;
        }else {
            s =0- (random.nextInt(max)%(max-min+1) + min);
        }

        return  s;
    }

    /**
     *  求比例-===- 通过除以2个正整数数的最大公约数来判定
     * @param firtst
     * @param second
     * @return
     */
    public static String   gcdProportion( Integer firtst,Integer second){

        if(firtst==0 ||second==0){
            return  firtst+":"+second ;
        }

        BigInteger num1 =  BigInteger.valueOf(firtst);
        BigInteger num2 =  BigInteger.valueOf(second);
        BigInteger gcd = num1.gcd(num2); // 计算得出的最大公约数

        BigInteger fir=num1.divide(gcd);
        BigInteger sec=num2.divide(gcd);
        String str= fir.toString()+":"+sec.toString();

        return  str;
    }

    /**
     *  求两个数的比,让其中一个数为1
     * @return
     */
    public static String  proportion( Integer firtst,Integer second){
        //判断两个数是否相等
        if( firtst==0 && second!=0){
            return 0+":"+second;
        }else if(second==0 && firtst !=0){
            return  firtst+":"+0;
        }else if(firtst==0 && second==0) {
            return     "0:0";
        }

        if(firtst.equals(second)){
            return "1:1";
        } else if(firtst>second){ //1大于2
            Double num=Double.parseDouble(new DecimalFormat("#.##").format(firtst/second));
            return   num+":1";
        }else if(firtst<second){//1小于2
            Double num=Double.parseDouble(new DecimalFormat("#.##").format(second/firtst));
            return  "1:"+num;

        }
        return  null;
    }

    /**
     *   获取整数
     * @param str
     * @return
     */
    public static Integer getNullorIntegr(Object str) {
        if (str == null ) {
            return 0;
        } else {
            if(StringUtils.isEmpty(str.toString())){
                return 0;
            }else {
                return Integer.parseInt(str.toString());
            }
        }
    }
    /**
     *   获取双精度数
     * @param str
     * @return
     */

    public static double getNullorDouble(Object str) {
        if (str == null ) {
            return 0;
        } else {
            if(StringUtils.isEmpty(str.toString())){
                return 0;
            }else {
                return  Double.valueOf(str.toString());
            }
        }
    }


    /**
     *   获取BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal getNullorBigdecmal(Object str) {

        if (str == null) {
            return BigDecimal.ZERO;
        } else {
            if(StringUtils.isEmpty(str.toString())){
                return BigDecimal.ZERO;
            }else {
                return BigDecimal.valueOf(Float.valueOf(str.toString()));
            }

        }
    }


    //率求值
    public  static BigDecimal getBl(Integer cs, Integer bcs) {
        return BigDecimal.valueOf(cs).divide(BigDecimal.valueOf(bcs));
    }

    //求除数
    public  static BigDecimal bigDecimalDivide(Integer cs, Integer bcs) {
        if(cs==0){
            return BigDecimal.valueOf(0);
        }
        if(bcs==0){
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(cs).divide(BigDecimal.valueOf(bcs));
    }



    //求和
    public  static BigDecimal bigDecimalAdd(BigDecimal value1, BigDecimal value2) {
        if(value1==null){
            value1=BigDecimal.valueOf(0);
        }
        if(value2==null){
            value2=BigDecimal.valueOf(0);
        }
        return value1.add(value2 );
    }

    //求和
    public  static BigDecimal bigDecimalAdd(Integer value1, BigDecimal value2) {
        if(value1==null){
            value1=0;
        }
        if(value2==null){
            value2=BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(value1).add(value2 );
    }

    //求和
    public  static BigDecimal bigDecimalAdd(BigDecimal value1, Integer value2) {
        if(value1==null){
            value1=BigDecimal.valueOf(0);
        }
        if(value2==null){
            value2=0;
        }
        return value1.add( BigDecimal.valueOf(value2) );
    }
    //求和
    public  static BigDecimal bigDecimalAdd(Integer value1, Integer value2) {
        if(value1==null){
            value1=0;
        }
        if(value2==null){
            value2=0;
        }
        return BigDecimal.valueOf(value1).add( BigDecimal.valueOf(value2) );
    }



    //求差
    public  static BigDecimal bigDecimalSubtract(BigDecimal value1, BigDecimal value2) {
        if(value1==null){
            value1=BigDecimal.valueOf(0);
        }
        if(value2==null){
            value2=BigDecimal.valueOf(0);
        }
        return value1.subtract(value2 );
    }

    //求差
    public  static BigDecimal bigDecimalSubtract(Integer value1, BigDecimal value2) {
        if(value1==null){
            value1=0;
        }
        if(value2==null){
            value2=BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(value1).subtract(value2 );
    }

    //求差
    public  static BigDecimal bigDecimalSubtract(BigDecimal value1, Integer value2) {
        if(value1==null){
            value1=BigDecimal.valueOf(0);
        }
        if(value2==null){
            value2=0;
        }
        return value1.subtract(BigDecimal.valueOf(value2) );
    }

    //求差
    public  static BigDecimal bigDecimalSubtract(Integer value1, Integer value2) {
        if(value1==null){
            value1=0;
        }
        if(value2==null){
            value2=0;
        }
        return BigDecimal.valueOf(value1).subtract(BigDecimal.valueOf(value2) );
    }



}
