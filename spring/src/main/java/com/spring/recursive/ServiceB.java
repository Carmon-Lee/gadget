package com.spring.recursive;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceB {

    @Resource
    private ServiceA serviceA;

}
