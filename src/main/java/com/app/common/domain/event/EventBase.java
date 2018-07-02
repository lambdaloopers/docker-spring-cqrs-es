package com.app.common.domain.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.UUID;

public abstract class EventBase implements Event {

    private UUID id;
    private long createdAt;

    public EventBase() {
        id = UUID.randomUUID();
        createdAt = new Date().getTime();
    }

    public UUID id() {
        return id;
    }

    public long createdAt() {
        return createdAt;
    }

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        return new String(mapper.writeValueAsBytes(this));
    }
}
