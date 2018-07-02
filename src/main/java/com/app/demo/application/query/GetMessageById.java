package com.app.demo.application.query;

import com.app.demo.domain.entity.Message;
import com.app.demo.domain.service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetMessageById {

    @Autowired
    private MessageRepository repository;

    public Message execute(String messageId) {

        Optional<Message> optionalMessage = repository.findById(messageId);

        return optionalMessage.get();
    }
}
