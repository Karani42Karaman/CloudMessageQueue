package com.karani.rabbitmqproducer;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisher {

    private final MqttClient mqttClient;

    public MqttPublisher(@Value("${mqtt.broker}") String broker,
                         @Value("${mqtt.client-id}") String clientId) throws MqttException {
        this.mqttClient = new MqttClient(broker, clientId);
        this.mqttClient.connect();
    }

    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttClient.publish(topic, mqttMessage);
            System.out.println("Mesaj yayınlandı: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}