//package com.inspire12.practice.api.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.listener.ContainerProperties;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfig {
//
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServers;
//    @Value("${spring.kafka.consumer.group-id}")
//    private String groupId;
//    @Value("${spring.kafka.consumer.enable-auto-commit}")
//    private boolean enableAutoCommit;
//
//    @Value("${spring.kafka.consumer.auto-offset-reset}")
//    private String autoOffsetReset;
//    @Value("${spring.kafka.consumer.max-poll-records}")
//    private Integer maxPoolRecords;
//
//    @Bean(name = "kafkaTemplate")
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean(name = "kafkaManualContainerFactory")
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaManualContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setConsumerFactory(consumerFactory(enableAutoCommit));
//        factory.setConcurrency(3);
//
//        factory.getContainerProperties()
//                .setPollTimeout(3000);
//
//        factory.getContainerProperties()
//                .setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        factory.getContainerProperties()
//                .setPollTimeout(15000);
//
//        return factory;
//    }
//
//    @Bean(name = "kafkaContainerFactory")
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(true));
//        factory.setConcurrency(3);
//
//        factory.getContainerProperties()
//                .setPollTimeout(3000);
////        factory.getContainerProperties()
////                .setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        factory.getContainerProperties()
//                .setPollTimeout(15000);
//        return factory;
//    }
//
//
//    private ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    public ConsumerFactory<String, String> consumerFactory(boolean autoCommit) {
//        if (autoCommit) {
//            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//        } else {
//            return new DefaultKafkaConsumerFactory<>(consumerManualConfigs());
//        }
//
//    }
//
//    private Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.RETRIES_CONFIG, 10);
//        props.put(ProducerConfig.ACKS_CONFIG, "all");
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
//
//    private Map<String, Object> consumerManualConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
//        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 104857600);
//        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 52428800);
//
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPoolRecords); //max.poll.records
//        //db 적재 하는데 오래 걸리는게 있으므로 시간을 늘려준다.
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000 * 60 * 30); //max.poll.records 만큼 가져왔을 때, 처리시간이 max.poll.interval.ms 값보다 작아야 함.
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 1000 * 60);
//        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000 * 20); // SESSION_TIMEOUT_MS_CONFIG 의 1/3의 값을 기입
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return props;
//    }
//
//    private Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
//
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPoolRecords);
//        //db 적재 하는데 오래 걸리는게 있으므로 시간을 늘려준다.
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000 * 60 * 30); //max.poll.records 만큼 가져왔을 때, 처리시간이 max.poll.interval.ms 값보다 작아야 함.
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 1000 * 60);
//        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000 * 20); // SESSION_TIMEOUT_MS_CONFIG 의 1/3의 값을 기입
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return props;
//    }
//}
