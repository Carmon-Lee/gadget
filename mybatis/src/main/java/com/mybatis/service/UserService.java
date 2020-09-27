/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.mybatis.service;

import com.mybatis.domain.User;
import com.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liguang
 * @version UserService.java, v 0.1 2020年09月01日 9:54 下午
 */
@RestController
@RequestMapping("/user")
public class UserService {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/all")
    public List<User> getUsers() {
        return userMapper.getAllUsers();
    }

}
