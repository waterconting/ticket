package com.ticket.ticket.utils;

import java.lang.reflect.Field;

/**
 *  复制工具类
 */
public class BeanCopyUtils {


    /**
     * 复制信息：要求要有一样的属性名
     * @param source 来源
     * @param target 目标
     * @return
     */
      public static  Object  Copy(Object source,Object  target ){
          if (null == source) {
              return  null;
          }
          try {
              for (Field src : source.getClass().getDeclaredFields()) {
                  src.setAccessible(true);
                  for (Field tar : target.getClass().getDeclaredFields()) {
                      tar.setAccessible(true);
                      if( src.getName()==tar.getName()){//同一个属性名
                          tar.set(target ,src.get(source));
                          break; //停止循环，进入下一轮
                      }
                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
           return  target;
      }




}
