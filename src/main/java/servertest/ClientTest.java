package servertest;

import game.PlayerData;

import java.io.*;
import java.net.Socket;

public class ClientTest {

    private int port;
    private String host;
    private PlayerData playerData;

    public ClientTest(int port, String host, String name) {
        this.port = port;
        this.host = host;
        playerData = new PlayerData(name);
    }

    public void test() {
        try {
            Socket socket = new Socket(host, port);
            String message = "Hello there, I'm " + playerData.getName();
            sendPlayerData(socket, playerData);
            System.out.println(readMessage(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerData(Socket socket, PlayerData playerData){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(playerData);
            outputStream.close();
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
