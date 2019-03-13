package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/3/13.
 */
@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    @StreamListener(StreamClient.OUTPUT)
    public void process(Object message) {
        log.info("StreamReceiver : {}", message);
    }

//    @StreamListener(StreamClient.INPUT)
//    @SendTo(StreamClient.OUTPUT)
//    public String  process2(Object message) {
//        log.info("StreamReceiver : {}", message);
//        return "receive msg " + message;
//    }
}
