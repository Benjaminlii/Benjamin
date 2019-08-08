package com.thoughtCoding.theMall.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MQTTUtil {

    private Logger logger = LogManager.getLogger(MQTTUtil.class);

    @Autowired
    private MqttPahoMessageHandler mqttHandler;

    public void send(String topic, String content) {
        // 构建消息
        Message<String> messages = MessageBuilder.withPayload(content).setHeader(MqttHeaders.TOPIC, topic).build();
        // 发送消息
        mqttHandler.handleMessage(messages);

        logger.info("[MQTT]-->top=[" + topic + "], message=[" + content + "]");
    }
}
