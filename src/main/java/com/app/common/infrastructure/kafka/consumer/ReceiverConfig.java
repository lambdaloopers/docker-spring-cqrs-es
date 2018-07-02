package com.app.common.infrastructure.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReceiverConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    private long initialIntervalMiliSeconds = 1000;

    private double intervalMultiplier = 2.0;

    private long maxIntervalMiliSeconds = 60000;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections
        // to the Kakfa cluster
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        // handler groups allow a pool of processes to divide the work of
        // consuming and processing records
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());

        return factory;
    }

    @Bean
    protected RetryTemplate retryTemplate() {
        RetryTemplate retry = new RetryTemplate();

        Map<Class<? extends Throwable>, Boolean> stopExceptions =
                Collections.singletonMap(InterruptedException.class, Boolean.FALSE);

        SimpleRetryPolicy retryPolicy =
                new SimpleRetryPolicy(Integer.MAX_VALUE, stopExceptions, true, true);

        retry.setRetryPolicy(retryPolicy);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialIntervalMiliSeconds);
        backOffPolicy.setMultiplier(intervalMultiplier);
        backOffPolicy.setMaxInterval(maxIntervalMiliSeconds);

        retry.setBackOffPolicy(backOffPolicy);

        return retry;
    }

    @Bean
    public AppConsumer appConsumer() {
        return new AppConsumer();
    }

}

