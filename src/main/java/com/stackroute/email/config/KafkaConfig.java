package com.stackroute.email.config;

import com.stackroute.email.domain.AlertMail;
//import com.stackroute.email.domain.Domain;
//import com.stackroute.kafkamailservice.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import javax.sound.midi.Receiver;
import java.util.HashMap;
import java.util.Map;
//configuration tells that the class having bean
@Configuration
@EnableKafka
public class KafkaConfig
{
//    @Value("${spring.kafka.bootstrap-servers}")
//
//    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, AlertMail> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new JsonDeserializer<>(AlertMail.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AlertMail> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AlertMail> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

//    @Bean
//    public Receiver receiver() {
//        return receiver();
//    }
}

