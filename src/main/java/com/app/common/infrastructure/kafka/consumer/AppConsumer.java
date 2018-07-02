package com.app.common.infrastructure.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.common.domain.event.EventType;
import com.app.common.domain.event.EventVersion;
import com.app.common.domain.service.factory.ProcessorFactory;
import com.app.common.domain.service.processor.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class AppConsumer {

    private static final Logger logger = LoggerFactory
            .getLogger(AppConsumer.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProcessorFactory processorFactory;

    @PostConstruct
    public void init() {
        mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumer-group-id}")
    public void consume(String message) {

        try {
            JsonNode jsonEvent = mapper.readTree(message);

            if (!EventType.contains(jsonEvent.get("type").textValue())) {
                logger.info("This event type doesn't match with any type: " + jsonEvent.get("type").textValue());
            }

            EventType eventType = EventType.valueOf(
                    jsonEvent.get("type").textValue());

            EventVersion eventVersion = EventVersion.valueOf(
                    jsonEvent.get("version").textValue());

            EventProcessor eventProcessor = processorFactory
                    .createProcessor(eventType, eventVersion);

            eventProcessor.process(message);
        } catch (IOException ex) {
            logger.error("Error deserializing the event: " + ex.toString());
        } catch (Exception ex) {
            logger.error("Error processing the app event: " + ex.toString());
        }
    }
}
