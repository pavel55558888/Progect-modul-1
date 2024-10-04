package com.example.progect;

import com.example.progect.marketplace.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgectApplication {
    @Autowired
    KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(ProgectApplication.class, args);
    }

}
