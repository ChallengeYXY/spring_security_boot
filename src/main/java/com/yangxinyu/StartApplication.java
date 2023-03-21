package com.yangxinyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu
 * @Date : 2023/3/20 13:23
 * @Author : 星宇
 * @Description :
 */
@SpringBootApplication
@MapperScan("com.yangxinyu.app.mapper")
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }
}
