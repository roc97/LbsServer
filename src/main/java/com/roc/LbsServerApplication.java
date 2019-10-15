package com.roc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.roc.mapper")
public class LbsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LbsServerApplication.class, args);
    }

}
