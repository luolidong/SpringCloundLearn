package com.imooc.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2019/2/28.
 */

@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product msg";
    }
}
