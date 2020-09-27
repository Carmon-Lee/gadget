package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketDemo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(osw);
//            String s = "Hello world\n";
//            writer.write(s);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String s = scanner.next();
//                System.out.println(s);
                System.out.println(s);
                writer.write(s + "\n");
                writer.flush();
                if (s.equals("end"))
                    break;
            }
            System.out.println("ready to close...");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
