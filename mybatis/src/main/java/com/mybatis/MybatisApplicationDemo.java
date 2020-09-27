/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liguang
 * @version MybatisApplicationDemo.java, v 0.1 2020年09月01日 9:38 下午
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis.mapper"})
public class MybatisApplicationDemo {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplicationDemo.class, args);
    }
}
