/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author liguang
 * @version Demo.java, v 0.1 2020年10月21日 16:18
 */
public class Demo {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        IntBuffer intBuffer = IntBuffer.allocate(1);
        intBuffer.put(1024);
        intBuffer.flip();
        System.out.println(intBuffer.get());

        buffer.putInt(10);
        buffer.flip();

        System.out.println(buffer.getInt());;
    }
}