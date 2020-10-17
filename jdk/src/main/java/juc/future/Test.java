/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package juc.future;

/**
 * @author liguang
 * @version Test.java, v 0.1 2020年10月17日 10:49
 */
public class Test {

    public static void main(String[] args) {
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start thread");
        new Thread(()->{}).start();

    }
}