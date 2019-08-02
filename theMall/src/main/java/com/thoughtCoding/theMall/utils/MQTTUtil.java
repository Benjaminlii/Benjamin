package com.thoughtCoding.theMall.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class MQTTUtil {
    @Autowired
    private static MqttPahoMessageHandler mqttHandler;

    public static void send(String topic, String content) {
        // 构建消息
        Message<String> messages = MessageBuilder.withPayload(content).setHeader(MqttHeaders.TOPIC, topic).build();
        // 发送消息
        mqttHandler.handleMessage(messages);
    }
}
