package com.app.demo.application.command.message;

import com.app.common.infrastructure.kafka.producer.KafkaProducer;
import com.app.demo.domain.entity.Message;
import com.app.demo.domain.event.user.MessageCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreateMessage {

    private final KafkaProducer producer;

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    public CreateMessage(
            KafkaProducer producer) {

        this.producer = producer;
    }

    public String execute(
            String sender,
            String content) {

        Message message = new Message(sender, content);

        producer.sendMessage(topic, new MessageCreated(
                message.getId(),
                message.getSender(),
                message.getContent()
        ));

        return message.getId();
    }
}
