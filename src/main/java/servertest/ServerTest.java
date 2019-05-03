package servertest;

import game.GameData;
import game.PlayerData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest implements Runnable {

    private int port;
    private boolean running, gameStarted;
    private ArrayList<Socket> players;
    private GameData gameData;

    public ServerTest(int port){
        this.port = port;
        this.running = false;
        this.gameStarted = false;
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
            while (running) {
                if(!gameStarted) {
                    if (gameData.getPlayers().size() < 2) {
                        players.add(waitForConnection(serverSocket));
                    }
                    if (gameData.getPlayers().size() == 2) {
                        gameData.drawCards();
                        gameData.getPlayers().get(0).setOpponent(gameData.getPlayers().get(1));
                        gameData.getPlayers().get(1).setOpponent(gameData.getPlayers().get(0));
                        readData(players.get(0));
                        readData(players.get(1));
                        sendData(players.get(0), gameData);
                        sendData(players.get(1), gameData);
                        gameStarted = true;
                    }
                } else {
                    readData(players.get(0));
                    readData(players.get(1));
                    System.out.println(gameData.getPlayers().get(0).getName() + "'s cards: ");
                    for (int i = 0; i < gameData.getPlayers().get(0).getDeck().size(); i++) {
                        System.out.println(gameData.getPlayers().get(0).getDeck().get(i).getName());
                    }
                    System.out.println(gameData.getPlayers().get(1).getName() + "'s cards: ");
                    for (int i = 0; i < gameData.getPlayers().get(1).getDeck().size(); i++) {
                        System.out.println(gameData.getPlayers().get(1).getDeck().get(i).getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        run();
    }
}
