package com.ticket.ticket;

import com.ticket.ticket.common.BaseBatchUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@EnableAsync        //开启异步功能
@EnableScheduling  //开启定时任务
@ServletComponentScan
@EnableJpaRepositories(repositoryBaseClass = BaseBatchUtils.class)
@SpringBootApplication
@EnableSwagger2
public class TicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketApplication.class, args);
    }

    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                System.out.println(source);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                if (StringUtils.isEmpty(source)) {
                    return date;
                }
                try {
                    date = sdf.parse(source);
                } catch (ParseException e2) {
                    try {
                        date = sdfday.parse(source);
                    } catch (ParseException e) {
                        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
                        try {
                            date = df.parse(source);

                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
                return date;
            }
        };
    }


    @Bean
    public Converter<String, String> addNewConvert2() {
        return new Converter<String, String>() {

            @Override
            public String convert(String s) {
                if ("null".equals(s)) {
                    s = null;
                }
                if ("".equals(s)) {
                    s = null;
                }
                return s;
            }
        };
    }

    @Bean
    public Converter<String, Integer> addNewConvert3() {
        return new Converter<String, Integer>() {
            @Override
            public Integer convert(String s) {
                Integer number;
                if ("null".equals(s)) {
                    number = 0;
                } else if (StringUtils.isEmpty(s)) {
                    number = 0;
                } else {
                    number = Integer.parseInt(s);
                }
                return number;
            }
        };
    }

}
