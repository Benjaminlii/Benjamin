package com.benjamin.springboot.bean;

import org.springframework.stereotype.Component;

/**
 * ClassName:Dog
 * Package:com.benjamin.springboot.bean
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-8-10 下午4:42
 */

@Component
public class Dog {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
