package com.sentiment.worker.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TweetStreams {

    String INPUT = "incoming-tweets";

    @Input(INPUT)
    SubscribableChannel inboundTweet();

    @Output(INPUT)
    MessageChannel outboundSentimentEvents();

}
