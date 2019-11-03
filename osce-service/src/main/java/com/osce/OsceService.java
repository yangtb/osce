package com.osce;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName: OsceServiceBoot
 * @Description: service启动类
 * @Author yangtongbin
 * @Date 2019-05-01
 */
@EnableScheduling
@EnableDubbo
@SpringBootApplication(scanBasePackages = { "com.osce.*"})
@MapperScan(value = "com.osce.orm.*")
public class OsceService {

    public static void main(String[] args) {
        SpringApplication.run(OsceService.class, args);
    }

}
