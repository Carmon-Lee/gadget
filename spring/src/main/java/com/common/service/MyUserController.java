package com.common.service;


import lombok.Data;

@Data
public class MyUserController {

    private MyUserService myUserService;

    private MyUserDao myUserDao;

    @Override
    public String toString() {
        return "MyUserController{}";
    }
}
