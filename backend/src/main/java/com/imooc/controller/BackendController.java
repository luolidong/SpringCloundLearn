package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by dell on 2019/3/21.
 */
@RestController

public class BackendController {

    @GetMapping("/api")
    public String printDate() {
        return new Date().toString() + "by hongxi";
    }
}
