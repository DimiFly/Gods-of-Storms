package servertest;

import game.GameData;
import game.PlayerData;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest implements Runnable{

    private int port;
    private String host;
    private PlayerData playerData;
    private GameData gameData;
    private Socket socket;
    private boolean running;

    public ClientTest(int port, String host, String name) {
        this.port = port;
        this.host = host;
        playerData = new PlayerData(name);
        running = false;
        run();
    }

    public void connectToServer(){
        try {
            socket = new Socket(host, port);
            sendPlayerData(socket, playerData);
            readGameData(socket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromServer(){
        try {
            running = false;
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
            if(gameData.getPlayers().get(0).getName().equals(playerData.getName())){
                playerData = gameData.getPlayers().get(0);
            } else {
                playerData = gameData.getPlayers().get(1);
            }
            if(playerData.getOpponent() != null) {
                System.out.println(playerData.getOpponent().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        connectToServer();
        while (running){
            sendPlayerData(socket, playerData);
            readGameData(socket);
        }
    }
}
