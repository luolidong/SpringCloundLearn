package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by dell on 2019/3/20.
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    //降级不止是调用服务异常，也可内部服务异常
    //超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
//    @HystrixCommand(fallbackMethod = "fallback")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //设置熔断
//
//            //设置在滚动时间窗口中，断路器的最小请求数
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), //
//
//            // 断路器打开之后，休眠时间窗开始计时，休眠时间窗到期，断路器进入半开状态，允许1个请求进入主逻辑，若正常则断路器闭合，若失败则打开断路器，重新计时
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
//
//            // 设置断路器打开的错误百分比。在滚动时间窗口中的错误率
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
//    })
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder",
                Arrays.asList("123456"),
                String.class);

//        throw new RuntimeException("busy now");
    }

    private String fallback() {
        return "太拥挤了，请稍后再试~";
    }

    private String defaultFallback() {
        return "默认： 太拥挤了，请稍后再试~";
    }
}
