package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


/**
 * 多路复用，是基于非阻塞IO功能上实现的一种监控IO连接的方式
 *
 */
public class SelectMultiplexServer {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();

            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(9090));
            ss.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            SelectionKey register = ss.register(selector, SelectionKey.OP_ACCEPT);

            int select;
            while ((select = selector.select(1000)) != -1) {
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel)key.channel();
                        int read = channel.read(byteBuffer);
                        if (read>0) {
                            byte[] bytes= new byte[read];
                            byteBuffer.flip();
                            byteBuffer.get(bytes);
                            System.out.println(new String(bytes));
                            channel.close();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
