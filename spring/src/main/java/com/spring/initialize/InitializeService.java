package com.spring.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class InitializeService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
