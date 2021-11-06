package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liguang
 * Created on 2021-09-25
 */
public class SocketOptionDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel channel = ServerSocketChannel.open();

//        channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        channel.bind(new InetSocketAddress(8080));
        System.out.println("bind");
        channel.accept();
        channel.close();

        channel = ServerSocketChannel.open();
        channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        channel.bind(new InetSocketAddress(8080));
        System.out.println("bind again");
        channel.accept();

    }
}
