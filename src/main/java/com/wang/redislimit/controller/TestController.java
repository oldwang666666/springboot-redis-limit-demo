package com.wang.redislimit.controller;

import com.wang.limit.annotation.AspectLimit;
import com.wang.limit.annotation.ControllerLimit;
import com.wang.redislimit.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/getLimit")
    public String getLimit() {
        try {
            return testService.getTestOne();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ControllerLimit(limit = 2)
    @RequestMapping("/getControllerLimit")
    public String getControllerLimit() {

        return testService.getTestTwo();
    }


}
