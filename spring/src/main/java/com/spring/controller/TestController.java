package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liguang
 * Created on 2021-08-29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/echo")
    public String echo() {
        return "echo";
    }
}
