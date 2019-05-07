package com.osce.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

/**
 * @ClassName: OsceServerBoot
 * @Description: 启动类
 * @Author yangtongbin
 * @Date 2019-05-01
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@EnableDubbo
public class OsceServer {

    public static void main(String[] args) {
        SpringApplication.run(OsceServer.class, args);
    }

}
