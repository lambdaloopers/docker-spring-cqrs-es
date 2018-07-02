package com.app.demo.domain.event.user;

import com.app.common.domain.event.EventBase;
import com.app.common.domain.event.EventType;
import com.app.common.domain.event.EventVersion;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageCreated extends EventBase {

    private String type = EventType.MessageCreated.toString();
    private String version = EventVersion.v1.toString();

    private String messageId;
    private String messageSender;
    private String messageContent;

    @JsonCreator
    public MessageCreated(
            @JsonProperty("messageId") String messageId,
            @JsonProperty("messageSender") String messageSender,
            @JsonProperty("messageContent") String messageContent) {

        super();

        this.messageId = messageId;
        this.messageSender = messageSender;
        this.messageContent = messageContent;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String version() {
        return version;
    }
}
