package com.app.demo.domain.entity;

import com.app.common.domain.entity.EntityBase;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@Where(clause = "deleted_at is null")
public class Message extends EntityBase {

    @Column(name = "sender")
    private String sender;

    @Column(name = "content")
    private String content;

    public Message() {
    }

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public Message(String id, String sender, String content) {
        super(id);
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
