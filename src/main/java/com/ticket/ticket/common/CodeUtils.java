package com.ticket.ticket.common;

import com.ticket.ticket.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class CodeUtils {
    @Autowired
    RedisUtil redisUtil;

    public String getOrderId(String prefix, String key, String variableValue) {
        //生成订单号 redis incr
        if (variableValue != null && variableValue.length() > 2) {
            variableValue = variableValue.substring(variableValue.length() - 2);
        } else {
            variableValue = String.format("%02d", new Random().nextInt(99));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        String time = sdf.format(new Date());
        //当天流水7位
        long tx = redisUtil.hincr(key, key, 1);
        if (tx == 1) {
            redisUtil.expire(key, 300);
        }
        //自动补0
        String end = autoAddZero(String.valueOf(tx));
        return prefix + time + end + variableValue;
    }

    public String autoAddZero(String str) {
        Integer intvalue = Integer.parseInt(str);
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(intvalue);
    }
}
