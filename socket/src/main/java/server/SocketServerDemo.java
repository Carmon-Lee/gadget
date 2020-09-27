package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(isr);

            while (true){
                char c = (char) isr.read();
                System.out.print(c);
                if (c == (char)-1){
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
