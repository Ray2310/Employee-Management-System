package com.rayce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    /*
        配置根目录下的东西可以在这里配置
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        /*将/main.html作为一个跳板，放置用户信息被揭密，*/
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //往容器中注入组件
    //自定义的国际化组件就会生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    //添加拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        //登录拦截器 ，拦截哪些选择 ，排除哪些选择
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html",
                "/user/login","/","/css/**","/dist/**","/docs.4.0/**","/js/**","/img/**"
        ,"/index.html(l='zh_CN')","/index.html(l='en_US')","/user/LogOut");
    }
}
