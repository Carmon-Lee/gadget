/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package lang;

/**
 * @author liguang
 * @version B.java, v 0.1 2020年10月19日 11:20
 */
public class B extends A {

    private String f1;

    @Override
    public String getF1() {
        return f1;
    }

    @Override
    public void setF1(String f1) {
        this.f1 = f1;
    }


    public static void main(String[] args) {
        B b = new B();
        b.setF1("a");
        System.out.println("");
    }
}