package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 多路复用，是基于非阻塞IO功能上实现的一种监控IO连接的方式
 */
public class SelectMultiplexServer {


    static class ConnectTask implements Runnable {
        private Selector connectSelector;
        private Selector messageSelector;

        public ConnectTask(Selector connectSelector, Selector messageSelector) {
            this.connectSelector = connectSelector;
            this.messageSelector = messageSelector;
        }

        @Override
        public void run() {
            try {
                ServerSocketChannel ss = ServerSocketChannel.open();
                ss.bind(new InetSocketAddress(9090));
                ss.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                SelectionKey register = ss.register(connectSelector, SelectionKey.OP_ACCEPT);

                int select;
                while ((select = connectSelector.select(1000)) != -1) {
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = connectSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
                            client.configureBlocking(false);
                            client.register(messageSelector, SelectionKey.OP_READ);
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class MessageTask implements Runnable {
        private Selector messageSelector;

        public MessageTask(Selector messageSelector) {
            this.messageSelector = messageSelector;
        }

        @Override
        public void run() {
            int select;
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            try {
                while ((select = messageSelector.select(1000)) != -1) {
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = messageSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();
                            int read = channel.read(byteBuffer);
                            if (read > 0) {
                                byte[] bytes = new byte[read];
                                byteBuffer.flip();
                                byteBuffer.get(bytes);
                                System.out.println(new String(bytes));
                                channel.close();
                            }
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Selector connectSelector = Selector.open();
        Selector messageSelector = Selector.open();
        ConnectTask connectTask = new ConnectTask(connectSelector, messageSelector);
        MessageTask messageTask = new MessageTask(messageSelector);

        new Thread(connectTask).start();
        new Thread(messageTask).start();
    }
}
