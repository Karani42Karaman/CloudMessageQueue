package com.karani.rabbitmqmqttconsumer;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class MqttConsumer {
    private String topic;
    private String payload;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC, String.class);
        String payload = message.getPayload();

        System.out.println("📥 MQTT Mesajı Alındı!");
        System.out.println("📌 Topic: " + topic);
        System.out.println("📌 Mesaj: " + payload);
        this.payload = payload;
        this.topic = topic;
    }


    public  void setMessage(String topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }
    public  String getTopic() {
        return topic;
    }

    public String getPayload() {
        return payload;
    }

}