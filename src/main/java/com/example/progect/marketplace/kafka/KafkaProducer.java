package com.example.progect.marketplace.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<Long, String > kafkaTemplate;

    public void sendMessage(String message){
        kafkaTemplate.send("TestTopic",message);
    }
}
