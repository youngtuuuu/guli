package edu.gdut.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 作者: xieburou
 * 日期: 2023/4/22 20:08
 * 概括:
 */
@SpringBootApplication
@ComponentScan(basePackages = "edu.gdut")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
