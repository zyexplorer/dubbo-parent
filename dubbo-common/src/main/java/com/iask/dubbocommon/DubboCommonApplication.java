package com.iask.dubbocommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.iask.dubbocommon.dao")
public class DubboCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboCommonApplication.class, args);
    }

}
