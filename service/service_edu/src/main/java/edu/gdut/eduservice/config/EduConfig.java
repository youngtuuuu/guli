package edu.gdut.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者: xieburou
 * 日期: 2023/4/22 20:15
 * 概括:
 */
@Configuration
@MapperScan("edu.gdut.eduservice.mapper")
public class EduConfig {

    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}
