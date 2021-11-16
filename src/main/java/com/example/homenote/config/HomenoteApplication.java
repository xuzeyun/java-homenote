package com.example.homenote.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 不管当前文件：启动类 放在任何目录，加这个注解可以扫描项目下所有类
@ComponentScan("com.example")

// springboot 启动最基本的注解
@SpringBootApplication

// 启动类添加mapper包扫描注解（否则程序运行报错）
@MapperScan("com.example.homenote.mapper")
public class HomenoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomenoteApplication.class, args);
    }

}
