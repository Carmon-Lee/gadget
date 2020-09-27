/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguang
 * @version Demo.java, v 0.1 2020年09月03日 8:28 下午
 */
public class BioClient {

    public static void main(String[] args) {
        new Thread(new BioServer()).start();

        AtomicInteger counter = new AtomicInteger();
        ExecutorService clientWorker = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            clientWorker.submit(new ClientWorker(counter));
        }
    }

    static class ClientWorker implements Runnable {
        AtomicInteger counter;

        public ClientWorker(AtomicInteger counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("localhost", 8888));
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();

                outputStream.write(("PING-"+counter.incrementAndGet()).getBytes());
                byte[] buffer = new byte[64];
                inputStream.read(buffer);

                System.out.println(new String(buffer));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
