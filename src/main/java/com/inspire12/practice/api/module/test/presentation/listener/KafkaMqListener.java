package com.inspire12.practice.api.module.test.presentation.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMqListener {

    @KafkaListener(id = "myId", topics = "topic1", autoStartup = "${kafka.enabled:false}")
    public void listen(String in) {
        System.out.println(in);
    }
}
