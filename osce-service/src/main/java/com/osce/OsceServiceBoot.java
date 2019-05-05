package com.osce;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: OsceServiceBoot
 * @Description: service启动类
 * @Author yangtongbin
 * @Date 2019-05-01
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = { "com.osce.*"})
@MapperScan(value = "com.osce.orm.*")
public class OsceServiceBoot {

    public static void main(String[] args) {
        SpringApplication.run(OsceServiceBoot.class, args);
    }

}
