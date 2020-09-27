package com.dubbo.consumer.service;

import com.dubbo.facade.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author liguang
 * @version StoreService.java, v 0.1 2020年09月07日 10:17 下午
 */
@Service
public class StoreService implements InitializingBean {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    public int getStoreUsers() {
        return userService.getUserCount();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(userService);
    }
}
