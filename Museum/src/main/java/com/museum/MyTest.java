package com.museum;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author wangzhao
 * @date 2019/7/24 13:32
 */

public class MyTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring/*.xml");
        String[] names = ac.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names));
    }

}
