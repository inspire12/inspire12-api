package com.inspire12.practice.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                   .partitions(10)
                   .replicas(1)
                   .build();
    }

}
