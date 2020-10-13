package com.ioc.my;

import com.my.ioc.twomap.MyBeanFactory;
import com.common.service.MyUserController;
import org.junit.Test;

public class MyBeanFactoryTest {


    @Test
    public void testGetBean() {
        MyBeanFactory beanFactory = new MyBeanFactory();
        System.out.println(beanFactory.getBean(MyUserController.class));;
    }
}