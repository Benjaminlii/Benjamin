package com.thoughtCoding.theMall;

import com.alibaba.fastjson.JSON;
import com.thoughtCoding.theMall.service.CustomerService;
import com.thoughtCoding.theMall.service.RecordService;
import com.thoughtCoding.theMall.utils.SignUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;
import java.util.*;

public class Test {
    private static MqttPahoMessageHandler mqttHandler;

    public static MqttPahoMessageHandler getMqttHandler() {
        return mqttHandler;
    }

    public static void setMqttHandler(MqttPahoMessageHandler mqttHandler) {
        Test.mqttHandler = mqttHandler;
    }

    public static void send(String topic, String content) {
        // 构建消息
        Message<String> messages = MessageBuilder.withPayload(content).setHeader(MqttHeaders.TOPIC, topic).build();
        // 发送消息
        mqttHandler.handleMessage(messages);
    }

    public static void main(String[] args) {
//        String path = "applicationContext.xml";
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
//
//        CustomerService customerService = (CustomerService) applicationContext.getBean("customerServiceImpl");
//        System.out.println(customerService.AddCustomer("hahaha", (byte) 1, (byte) 19, "13700000004",
//                new File("/media/benjamin/Data/Java/JavaStudy/out/production/JavaStudy/textJDBC/lion.jpg")));

//        Map<String, Object> map = new HashMap<>();
//        map.put("a", "a");
//        map.put("ac", "ae");
//        map.put("ab", "ab");
//        map.put("y", "y");
//        map.put("c", "c");
//        map.put("b", "b");
//        map.put("t", "t");
//        map.put("f", "f");
//        map.put("e", new Date().getTime());
//        System.out.println(SignUtil.getSign(map));

        //测试mqtt
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        Test.setMqttHandler((MqttPahoMessageHandler) applicationContext.getBean("mqttHandler"));

        Scanner in = new Scanner(System.in);
        while (true){
            Test.send("theMall", in.nextLine());
        }

    }
}
