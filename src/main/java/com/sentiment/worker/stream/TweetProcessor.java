package com.sentiment.worker.stream;

import com.sentiment.worker.dto.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.ParseException;


@Slf4j
@Component
public class TweetProcessor {


    @StreamListener(TweetStreams.INPUT)
    public void process(@Payload Tweet t) throws ParseException {
        log.info("******** Received results from kafka: {}", t);
    }


}
