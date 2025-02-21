package com.karani.rabbitmqproducer;

import com.karani.rabbitmqproducer.MqttPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {

    private final MqttPublisher mqttPublisher;

    @Value("${mqtt.topic}")
    private String defaultTopic;

    public ProducerController(MqttPublisher mqttPublisher) {
        this.mqttPublisher = mqttPublisher;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        mqttPublisher.publishMessage(defaultTopic, message);
        return "Mesaj gönderildi: " + message;
    }
}