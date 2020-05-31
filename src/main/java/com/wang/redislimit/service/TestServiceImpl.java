package com.wang.redislimit.service;

import com.wang.limit.annotation.AspectLimit;
import com.wang.limit.annotation.ControllerLimit;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @AspectLimit
    @Override
    public String getTestOne() {
        return "getTestOne";
    }

    @Override
    public String getTestTwo() {
        return "getTestTwo";
    }
}
