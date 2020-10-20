/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package juc.threadlocal;

/**
 * @author liguang
 * @version ThreadLocalDemo.java, v 0.1 2020年10月19日 17:20
 */
public class ThreadLocalDemo {

    public static ThreadLocal<ThreadLocalDemo> LOCAL = ThreadLocal.withInitial(ThreadLocalDemo::new);

    public ThreadLocalDemo() {
        System.out.println("constructor");
    }

    private String f1;

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }


    public static void main(String[] args) {
        ThreadLocalDemo demo = LOCAL.get();
        LOCAL.remove();
        String[] split = "a:".split(":");
        System.out.println();
    }
}