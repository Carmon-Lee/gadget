package com.dubbo.provider.service;

import com.dubbo.facade.UserService;

/**
 * @author liguang
 * @version UserServiceImpl.java, v 0.1 2020年09月07日 10:14 下午
 */
public class UserServiceImpl implements UserService {

    @Override
    public int getUserCount() {
        return 10086;
    }
}
