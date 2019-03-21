package servertest;

import java.io.*;
import java.net.Socket;

public class ClientTest {

    private int port;
    private String host;

    public ClientTest(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void test() {
        try {
            Socket socket = new Socket(host, port);
            String message = "Hello there, I'm the client";
            sendMessage(socket, message);
            System.out.println(readMessage(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Socket socket, String message){
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.print(message);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage(Socket socket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[200];
            int amoutChars = bufferedReader.read(buffer, 0, 200);
            String message = new String(buffer, 0, amoutChars);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
