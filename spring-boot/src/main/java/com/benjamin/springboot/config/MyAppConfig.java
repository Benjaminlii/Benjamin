package com.benjamin.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.benjamin.springboot.bean.Person;

/**
 * ClassName:MyAppConfig
 * Package:com.benjamin.springboot.config
 * <p>
 * Description:
 * 在这个类中学习使用注解的方式编写spring boot的配置类
 *
 * @author: Benjamin
 * @date: 20-2-12 下午5:02
 */

// 该注解指明当前类是个配置类,用来替代配置文件
@Configuration
public class MyAppConfig {

    /**
     * @Bean注解将该方法的返回值添加到容器中
     */
    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("Benjamin");
        return person;
    }
}
