package servertest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    private int port;

    public ServerTest(int port){
        this.port = port;
    }

    public void test(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket client = waitForConnection(serverSocket);
            System.out.println(readMessage(client));
            sendMessage(client, "Hey It's the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket waitForConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public void sendMessage(Socket socket, String message){
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.print(message);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
