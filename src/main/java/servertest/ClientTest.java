package servertest;

import game.GameData;
import game.PlayerData;

import java.io.*;
import java.net.Socket;

public class ClientTest {

    private int port;
    private String host;
    private PlayerData playerData;
    private GameData gameData;

    public ClientTest(int port, String host, String name) {
        this.port = port;
        this.host = host;
        playerData = new PlayerData(name);
        gameData = new GameData();
    }

    public void test() {
        try {
            Socket socket = new Socket(host, port);
            sendPlayerData(socket, playerData);
            readGameData(socket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerData(Socket socket, PlayerData playerData){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGameData(Socket socket){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            gameData = (GameData) inputStream.readObject();
            System.out.println(gameData.getAllCards().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
