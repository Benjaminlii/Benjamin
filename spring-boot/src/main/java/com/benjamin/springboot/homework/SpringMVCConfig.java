package com.benjamin.springboot.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ClassName:SpringMVCConfig
 * Package:com.benjamin.springboot.homework
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-12-11 上午9:39
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/modelA.html").setViewName("modelA");
                registry.addViewController("/modelB.html").setViewName("modelB");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AInterceptor()).addPathPatterns("/controllerA");
                registry.addInterceptor(new BInterceptor()).addPathPatterns("/controllerB");
            }
        };

        return adapter;
    }
}
