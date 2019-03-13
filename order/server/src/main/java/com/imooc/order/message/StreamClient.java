package com.imooc.order.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by dell on 2019/3/13.
 */
public interface StreamClient {

    String INPUT = "myMessageInput";
    String OUTPUT = "myMessageOutput";

//    @Input(StreamClient.INPUT)
//    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();
}
