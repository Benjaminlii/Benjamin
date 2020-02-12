package com.benjamin.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:Persen
 * Package:com.benjamin.springboot.bean
 * <p>
 * Description:
 *
 * @ConfigurationProperties(prefix = "persen")
 * 将全局配置文件中的person下的配置与本类中的属性进行绑定
 * 这个类需要被放入spring容器
 *
 * 如果将所有的配置全部放在一个全局配置文件中
 * 难免造成配置文件过于臃肿的结果
 * 那么,可以自定义更多的配置文件
 * 并且使用@PropertySource(value = "classpath:xxx.property")注解进行非全局配置文件的绑定
 *
 *
 * @author: Benjamin
 * @date: 19-8-10 下午4:40
 */

//@Component
//@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean aBoolean;
    private Date birth;

    private Map<String, Object> map;
    private List<String> list;
    private Dog dog;

    @Override
    public String toString() {
        return "Persen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", aBoolean=" + aBoolean +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
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

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
