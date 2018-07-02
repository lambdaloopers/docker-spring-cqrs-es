package com.app.common.domain.event;

import java.util.UUID;

public interface Event {

    UUID id();

    String type();

    String version();

    long createdAt();

    String serialize() throws Exception;
}
