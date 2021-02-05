package com.zbcn.combootes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zbcn.combootes.nba.dao")
public class ComBootEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComBootEsApplication.class, args);
    }

}
