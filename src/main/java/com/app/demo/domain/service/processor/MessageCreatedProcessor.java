package com.app.demo.domain.service.processor;

import com.app.common.domain.event.EventType;
import com.app.common.domain.event.EventVersion;
import com.app.common.domain.service.processor.EventProcessor;
import com.app.demo.domain.entity.Message;
import com.app.demo.domain.event.user.MessageCreated;
import com.app.demo.domain.service.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageCreatedProcessor implements EventProcessor {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void process(String eventMessage) throws IOException {

        MessageCreated event = mapper.readValue(eventMessage, MessageCreated.class);

        Message message = new Message(
                event.getMessageId(),
                event.getMessageSender(),
                event.getMessageContent()
        );

        messageRepository.save(message);
    }

    @Override
    public String eventType() {

        return EventType.MessageCreated.toString();
    }

    @Override
    public String eventVersion() {

        return EventVersion.v1.toString();
    }
}
