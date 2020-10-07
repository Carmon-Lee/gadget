
package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liguang
 * @version NioServer.java, v 0.1 2020年09月04日 10:08 下午
 */
public class NioServer implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.configureBlocking(false);
            ss.bind(new InetSocketAddress(8090));

            List<SocketChannel> clients = new ArrayList<>();
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);

            while (true) {
                SocketChannel clientChannel = ss.accept();
                Thread.sleep(1000);
                if (clientChannel != null) {
                    clientChannel.configureBlocking(false);
                    clients.add(clientChannel);
                    System.out.println("new connection established!");
                }
                for (SocketChannel client : clients) {
                    int read = client.read(byteBuffer);
                    if (read <= 0) {
                        continue;
                    }
                    System.out.println("received data...");
                    byteBuffer.flip();
                    byte[] buffer = new byte[byteBuffer.limit()];
                    byteBuffer.get(buffer);
                    System.out.println(new String(buffer));
                    byteBuffer.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new NioServer().run();
    }
}
