package com.easyboke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.easyboke.mappers")
public class EasyBokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBokeApplication.class, args);
    }
}
