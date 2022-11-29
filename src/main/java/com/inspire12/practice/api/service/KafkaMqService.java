package com.inspire12.practice.api.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMqService {

    @KafkaListener(id = "myId", topics = "topic1", autoStartup = "${kafka.enabled}")
    public void listen(String in) {
        System.out.println(in);
    }
}
