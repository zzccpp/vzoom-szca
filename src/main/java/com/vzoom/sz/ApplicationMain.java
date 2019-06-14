package com.vzoom.sz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-06-13 16:05
 * @describe vzoom-szca
 *
 * Spring-boot启动入口
 */
@SpringBootApplication
@EnableScheduling
public class ApplicationMain {

    public static void main(String[] args) {

        //String xx = "914403002795385521_深圳市洲际人才管理有限公司　　".trim();
        //System.out.println(xx+">>");

        SpringApplication.run(ApplicationMain.class,args);
    }
}
