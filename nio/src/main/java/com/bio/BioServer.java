/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liguang
 * @version BioWorker.java, v 0.1 2020年09月03日 8:15 下午
 */
public class BioServer implements Runnable {

    ServerSocket serverSocket;
    ExecutorService workerPool;

    public BioServer() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            //
        }
        workerPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                workerPool.submit(new Handler(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Handler implements Runnable {
        Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[64];
            try {
                socket.getInputStream().read(bytes);
                System.out.println(new String(bytes));

                TimeUnit.SECONDS.sleep(3);
                socket.getOutputStream().write("PONG".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
