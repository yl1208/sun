package com.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 在线考试系统启动类
 */
@SpringBootApplication
@MapperScan("com.exam.mapper")                     // MyBatis Mapper扫描
@EnableTransactionManagement                       // 开启事务支持
public class ExamsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsystemApplication.class, args);
    }
}