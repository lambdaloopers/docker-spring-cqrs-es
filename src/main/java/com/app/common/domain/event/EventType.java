package com.app.common.domain.event;

public enum EventType {
    MessageCreated("MessageCreated");

    private final String eventType;

    EventType(String eventType) {

        this.eventType = eventType;
    }

    @Override
    public String toString() {

        return eventType;
    }

    public static boolean contains(String eventName) {

        for (EventType c : EventType.values()) {
            if (c.name().equals(eventName)) {
                return true;
            }
        }

        return false;
    }
}
