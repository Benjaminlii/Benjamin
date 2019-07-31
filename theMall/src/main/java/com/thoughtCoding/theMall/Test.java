package com.thoughtCoding.theMall;

import com.thoughtCoding.theMall.service.RecordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        RecordService recordService = (RecordService) applicationContext.getBean("recordServiceImpl");
        System.out.println(recordService.queryCountByAge("T恤"));

    }
}
