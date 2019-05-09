package client;

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
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerData(Socket socket, PlayerData playerData){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(playerData);
            outputStream.flush();
        } catch (IOException e) {
            disconnectFromServer();
        }
    }

    public PlayerData readGameData(Socket socket){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return (PlayerData) inputStream.readObject();
        } catch (Exception e) {
            disconnectFromServer();
        } return null;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        running = true;
        connectToServer();
        while (running){
            while (!playerData.isStarted()) {
                sendPlayerData(socket, playerData);
                playerData = readGameData(socket);
                System.out.println("Not started");
            }
            //sendPlayerData(socket, playerData);
            System.out.println(playerData.getOpponent().getName());
            System.out.println("Read...");
            PlayerData data;
            while ((data = readGameData(socket)) != null) {
                playerData = data;
            }
        }
    }
}
