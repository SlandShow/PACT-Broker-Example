package com.slandshow.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/", description = "controller", produces = "application/json")
public class ExampleController {

    private final String[] mock = {"Moscow", "Tokyo", "Rome"};

    @GetMapping("/test")
    public String[] getTest() {
        return mock;
    }
}
