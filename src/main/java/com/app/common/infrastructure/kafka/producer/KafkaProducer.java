package com.app.common.infrastructure.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.app.common.domain.event.EventBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaProducer {

    private static final Logger logger = LoggerFactory
            .getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(String topic, EventBase message) {
        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        try {
            String messageContent = message.serialize();

            ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate
                    .send(topic, messageContent);

            future.addCallback(
                    new ListenableFutureCallback<SendResult<Integer, String>>() {

                        @Override
                        public void onSuccess(
                                SendResult<Integer, String> result) {
                            logger.info("sent message='{}' with offset={}",
                                    messageContent,
                                    result.getRecordMetadata().offset());
                        }

                        @Override
                        public void onFailure(Throwable ex) {
                            logger.error("unable to send message='{}'",
                                    message, ex);
                        }
                    });

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // you can register a callback with the listener to receive the result
        // of the send asynchronously

        // alternatively, to block the sending thread, to await the result,
        // invoke the futures get() method
    }
}
