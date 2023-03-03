package com.example.scskafkatest;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    @Input
    SubscribableChannel testInput();

    @Output
    MessageChannel testOutput();

}
