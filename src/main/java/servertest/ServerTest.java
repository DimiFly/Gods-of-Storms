package servertest;

import game.GameData;
import game.PlayerData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest implements Runnable {

    private int port;
    private boolean running;
    private ArrayList<Socket> players;
    private GameData gameData;

    public ServerTest(int port){
        this.port = port;
        this.running = false;
        gameData = new GameData();
        start();
    }

    public Socket waitForConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            PlayerData playerData = readData(socket);
            System.out.println("Player connected: " + playerData.getName());
            gameData.getPlayers().add(playerData);
            sendData(socket, gameData);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PlayerData readData(Socket socket){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return (PlayerData) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(Socket socket, GameData gameData){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(gameData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            players = new ArrayList<Socket>();
            System.out.println("in run");
            while (running) {
                players.add(waitForConnection(serverSocket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        run();
    }
}
