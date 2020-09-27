package com.ioc.my;

import com.ioc.my.service.MyUserController;
import org.junit.Test;

public class MyBeanFactoryTest {


    @Test
    public void testGetBean() {
        MyBeanFactory beanFactory = new MyBeanFactory();
        System.out.println(beanFactory.getBean(MyUserController.class));;
    }
}