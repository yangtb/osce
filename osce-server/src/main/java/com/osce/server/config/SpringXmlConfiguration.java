package com.osce.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName: SpringXmlConfiguration
 * @Description: spring boot引用spring xml的配置方式
 * @Author yangtongbin
 * @Date 2019-05-01
 */
@Configuration
@ImportResource(locations = { "classpath:spring-context.xml" })
public class SpringXmlConfiguration {

	
	
}
