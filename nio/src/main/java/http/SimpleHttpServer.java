package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;

import http.request.RequestProcessor;

/**
 * @author liguang
 * Created on 2021-08-27
 */
public class SimpleHttpServer {

    private ServerSocket serverSocket;
    private List<Socket> clients;
    private ExecutorService acceptPool;
    private ExecutorService requestHandlePool;

    public void init() throws IOException {
        System.out.println("start to init http server...");
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9999));
        clients = new ArrayList<>();

        acceptPool = Executors.newFixedThreadPool(1);
        requestHandlePool = Executors.newFixedThreadPool(10);
        System.out.println("init success!");
    }

    public void start() throws IOException {
        init();
        System.out.println("start http server");
        acceptPool.execute(new AcceptTask(serverSocket, new AtomicBoolean(false), clients, requestHandlePool));
    }

    static class AcceptTask implements Runnable {
        private ServerSocket serverSocket;
        private AtomicBoolean stopped;
        private List<Socket> clients;
        private ExecutorService requestHandlePool;

        public AcceptTask(ServerSocket serverSocket,
                AtomicBoolean stopped,
                List<Socket> clients,
                ExecutorService requestHandlePool) {
            this.serverSocket = serverSocket;
            this.stopped = stopped;
            this.clients = clients;
            this.requestHandlePool = requestHandlePool;
        }

        @Override
        public void run() {
            while (!stopped.get()) {
                try {
                    Socket client = serverSocket.accept();
                    System.out.println("client connection accepted, socket:" + client.getRemoteSocketAddress());
                    clients.add(client);
                    requestHandlePool.execute(new HttpTask(client));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class HttpTask implements Runnable {

        private Socket client;
        private RequestProcessor processor;

        public HttpTask(Socket client) {
            this.client = client;
            this.processor = new RequestProcessor();
        }

        @Override
        public void run() {
            String command = null;
            try {
                InputStream inputStream = client.getInputStream();
                int c;
                char[] buffer = new char[10 * 1024 * 1024];
                int len = 0;
                while ((c = inputStream.read()) != -1 && ((char) c) != '\n') {
                    buffer[len++] = (char) c;
                }
                command = processor.processCommand(new String(buffer, 0, len));
            } catch (Exception e) {
                e.printStackTrace();
            }

            writeMsg(command, client);
        }

        private void writeMsg(String msg, Socket client) {
            try {
                OutputStream stream = client.getOutputStream();
                stream.write(StringUtils.getIfEmpty(msg, () -> StringUtils.EMPTY).getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        SimpleHttpServer httpServer = new SimpleHttpServer();
        httpServer.start();
    }

}
