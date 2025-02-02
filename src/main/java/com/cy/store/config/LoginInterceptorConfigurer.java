package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util. List;

/**
 * 处理器拦截器的注册
 */
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单：存放在一个List集合中
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/web/reg");
        patterns.add("/web/login");
        //完成拦截器的注册/表示要拦截的URL是什么/除了什么方法之外
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
