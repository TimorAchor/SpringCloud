package com.timor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.timor.dao")
public class AdminsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminsApplication.class,args);
    }
}
