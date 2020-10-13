package com.common.service;


import lombok.Data;

@Data
public class MyUserService {

    private MyUserDao myUserDao;

    @Override
    public String toString() {
        return "MyUserService{}";
    }
}
