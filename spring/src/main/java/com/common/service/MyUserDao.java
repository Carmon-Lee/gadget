package com.common.service;


import lombok.Data;

@Data
public class MyUserDao {

    private MyUserController controller;

    @Override
    public String toString() {
        return "MyUserDao{}";
    }
}
