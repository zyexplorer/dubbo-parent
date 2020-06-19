package com.iask.dubboprovider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan({"com.iask.dubboapi.dao.mysql", "com.wenwo.platform.*"})
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
