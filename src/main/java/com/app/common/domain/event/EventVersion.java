package com.app.common.domain.event;

public enum EventVersion {
    v1("v1");

    private final String eventVersion;

    private EventVersion(final String eventVersion) {

        this.eventVersion = eventVersion;
    }

    @Override
    public String toString() {

        return eventVersion;
    }
}
