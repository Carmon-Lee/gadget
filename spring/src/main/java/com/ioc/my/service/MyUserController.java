package com.ioc.my.service;


import lombok.Data;

@Data
public class MyUserController {

    private MyUserService myUserService;

    private MyUserDao myUserDao;
}
