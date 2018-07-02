package com.app.demo.application.query;

import com.app.demo.domain.entity.Message;
import com.app.demo.domain.service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetAllMessages {

    @Autowired
    private MessageRepository repository;

    public Page<Message> execute(
            int offset,
            int limit) {
        return repository.findAllMessages(
                PageRequest.of(offset, limit));
    }
}
