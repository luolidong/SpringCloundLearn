package com.imooc.order.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by dell on 2019/3/4.
 */
@FeignClient(name = "product11")
public interface ProductClient1 {
//
//    @GetMapping("/msg")
//    String productMsg();
//
//    @PostMapping("/listForOrder")
//    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
//
//    @PostMapping("/decreaseStock")
//    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
//
//    @GetMapping("/product/test")
//    String test(@RequestParam("name") String name);
}
