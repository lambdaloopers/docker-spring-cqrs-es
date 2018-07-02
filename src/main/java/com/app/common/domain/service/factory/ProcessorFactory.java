package com.app.common.domain.service.factory;

import com.app.common.domain.event.EventType;
import com.app.common.domain.event.EventVersion;
import com.app.common.domain.exception.EventProcessorNotFoundException;
import com.app.common.domain.service.processor.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessorFactory {

    private Map<String, EventProcessor> eventProcessors;

    @Autowired
    public ProcessorFactory(List<EventProcessor> eventProcessors) {

        this.eventProcessors = new HashMap<>();

        for (EventProcessor eventProcessor : eventProcessors) {
            this.eventProcessors.put(
                    eventProcessor.eventType() + eventProcessor.eventVersion(),
                    eventProcessor);
        }
    }

    public EventProcessor createProcessor(EventType eventType, EventVersion eventVersion)
            throws EventProcessorNotFoundException {

        EventProcessor eventProcessor = eventProcessors
                .get(eventType.toString() + eventVersion.toString());

        if (eventProcessor == null) {
            throw new EventProcessorNotFoundException(
                    "No EventProcessor found for " + eventType + " (" + eventVersion + ")");
        }

        return eventProcessor;
    }
}
