package com.applicationcontext;

import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean
    public Foo foo() {
        return new Foo();
    }


    static class Foo {}
}
