package com.imooc.order.controller;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dell on 2019/2/28.
 */

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    //第二种方法
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    //第二种方法
//    @Autowired
//    private RestTemplate restTemplate;

//    @GetMapping("/getProductMsg")
//    public String getProductMsg() {
//        String response = productClient.productMsg();
//        //1. 第一种方式 (直接使用restTemplate，写死url)
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.getForObject("http://localhost:8080/msg",String.class);
////        log.info("response = {}", response);
//
//        //2. 第二种方式（先利用 LoadBalancerClient 通过应用获取url，再使用直接使用restTemplate）
////        RestTemplate restTemplate = new RestTemplate();
////        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
////        String url = String.format("http://%s:%s", serviceInstance.getHost(),serviceInstance.getPort()) + "/msg";
////        String response = restTemplate.getForObject(url,String.class);
//
//
//        //3. 第三种方式（利用@LoadBalanced，可在restTemplate里使用名字）
////        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//
//        return response;
//    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("123456"));
        log.info("response = {}", productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("123456", 10)));
        return "ok";
    }
}
