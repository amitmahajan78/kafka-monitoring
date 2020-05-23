package com.sentiment.worker.controller;

import com.sentiment.worker.dto.Tweet;
import com.sentiment.worker.stream.TweetStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentimentRestController {

    @Autowired
    TweetStreams tweetStreams;

    @PostMapping(path = "/publish")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sendTweet(@RequestBody Tweet tweet) {
        MessageChannel messageChannel = tweetStreams.outboundSentimentEvents();


        messageChannel.send(MessageBuilder
                .withPayload(tweet)
                .setHeader(KafkaHeaders.MESSAGE_KEY, tweet.getId())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        return "sent";
    }
}
