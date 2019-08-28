package com.osce.server.config;

import com.osce.server.interceptor.SessionTimeOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: SessionTimoutConfiguration
 * @Description: session超时处理
 * @Author yangtongbin
 * @Date 2019-05-03
 */
@Configuration
public class SessionTimoutConfiguration implements WebMvcConfigurer {

    @Bean
    public SessionTimeOutInterceptor loginInterceptor() {
        return new SessionTimeOutInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor());
        registration.addPathPatterns("/**/**");
        registration.excludePathPatterns("/login**");
        registration.excludePathPatterns("/mobile**");
        registration.excludePathPatterns("/login/**");
        registration.excludePathPatterns("/logout/**");
        registration.excludePathPatterns("/error**");
        registration.excludePathPatterns("/amazeui/**");
        registration.excludePathPatterns("/biz/**");
        registration.excludePathPatterns("/bootstrap/**");
        registration.excludePathPatterns("/common/**");
        registration.excludePathPatterns("/layui/**");
        registration.excludePathPatterns("/third/**");
        registration.excludePathPatterns("/ztree/**");
        registration.excludePathPatterns("/help/**");
        registration.excludePathPatterns("/");
        registration.excludePathPatterns("/login");
        registration.excludePathPatterns("/403");
        registration.excludePathPatterns("/404");
        registration.excludePathPatterns("/500");
        registration.excludePathPatterns("/pf/p/user/register/page");
        registration.excludePathPatterns("/pf/r/user/register/**");
        registration.excludePathPatterns("/r/**");
        registration.excludePathPatterns("/m/**");
        registration.excludePathPatterns("/index");
        registration.excludePathPatterns("/main.html");
    }

}
