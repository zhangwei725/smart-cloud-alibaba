package com.smart.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangwei
 */
@SpringBootApplication
@MapperScan("com.smart.auth.mapper")
public class SmartAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartAuthApplication.class, args);
    }
}
