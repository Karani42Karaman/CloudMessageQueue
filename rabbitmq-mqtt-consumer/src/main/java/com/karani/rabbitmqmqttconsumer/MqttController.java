package com.karani.rabbitmqmqttconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class MqttController {

    // Mesajları tutacağımız liste
    @Autowired
    private MqttConsumer mqttConsumer;
    private List<Map<String, String>> messages = new ArrayList<>();

    @GetMapping("/")
    public List<Map<String, String>> index() {
        String lastMessage = mqttConsumer.getPayload();
        String lastTopic = mqttConsumer.getTopic();
        Map<String, String> message = new HashMap<>();

        if(lastTopic != null) {
            message.put("topic", lastTopic);
            message.put("payload", lastMessage);
            messages.add(message);
            mqttConsumer.setMessage(null,null);
        }

        return messages; // JSON formatında döner
    }

    @PostMapping("/remove")
    public List<Map<String, String>> removeMessage(@RequestParam int index) {
        if (index >= 0 && index < messages.size()) {
            messages.remove(index); // Liste üzerinden mesajı siler
        }
        return messages; // Güncellenmiş listeyi döner
    }


}