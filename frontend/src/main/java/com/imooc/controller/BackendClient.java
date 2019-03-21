package com.imooc.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by dell on 2019/3/21.
 */
@FeignClient(name = "backend")
public interface BackendClient {
    @GetMapping("/api")
    String printDate();
}
