package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * 自己轮训监控连接中的事件
 */
public class SimpleMultiplexServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.configureBlocking(false);
            ss.bind(new InetSocketAddress(9090));
            List<SocketChannel> clients = new LinkedList<>();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (true) {
                SocketChannel client = ss.accept();
                Thread.sleep(5000);
                if (client!=null) {
                    client.configureBlocking(false);
                    clients.add(client);
                }

                for (SocketChannel cli : clients) {
                    int read = cli.read(byteBuffer);
                    if (read>0) {
                        byte[] bytes = new byte[read];
                        byteBuffer.flip();
                        byteBuffer.get(bytes);
                        System.out.println(new String(bytes));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
