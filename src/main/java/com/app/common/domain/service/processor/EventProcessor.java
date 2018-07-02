package com.app.common.domain.service.processor;

public interface EventProcessor {

    void process(String message) throws Exception;

    String eventType();

    String eventVersion();
}
