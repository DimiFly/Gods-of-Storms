package server;

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
    }

    public Socket waitForConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            PlayerData playerData = readData(socket);
            System.out.println("Player connected: " + playerData.getName());
            gameData.getPlayers().add(playerData);
            sendData(socket, playerData);
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

    public void sendData(Socket socket, PlayerData playerData){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(playerData);
            outputStream.flush();
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
        int counter = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            players = new ArrayList<Socket>();
            while (running) {
                if(!gameStarted) {
                    if (gameData.getPlayers().size() < 2) {
                        players.add(waitForConnection(serverSocket));
                    }
                    if (gameData.getPlayers().size() == 2) {
                        gameData.getPlayers().set(0, readData(players.get(0)));
                        gameData.getPlayers().set(1, readData(players.get(1)));
                        gameData.drawCards();
                        gameData.getPlayers().get(0).setOpponent(gameData.getPlayers().get(1));
                        gameData.getPlayers().get(1).setOpponent(gameData.getPlayers().get(0));
                        sendData(players.get(0), gameData.getPlayers().get(0));
                        sendData(players.get(1), gameData.getPlayers().get(1));
                        gameStarted = true;
                        gameData.getPlayers().set(0, readData(players.get(0)));
                        gameData.getPlayers().set(1, readData(players.get(1)));
                        gameData.getPlayers().get(0).setStarted(true);
                        gameData.getPlayers().get(1).setStarted(true);
                        gameData.getPlayers().get(0).setMyTurn(true);
                        sendData(players.get(0), gameData.getPlayers().get(0));
                        sendData(players.get(1), gameData.getPlayers().get(1));
                        System.out.println(readData(players.get(0)).isStarted());
                        System.out.println("Game started");
                    }
                } else {
/*
                    Thread.sleep(500);
                    gameData.getPlayers().set(0, readData(players.get(0)));
                    gameData.getPlayers().set(1, readData(players.get(1)));
                    gameData.getPlayers().get(0).setOpponent(gameData.getPlayers().get(1));
                    gameData.getPlayers().get(1).setOpponent(gameData.getPlayers().get(0));
                    sendData(players.get(0), gameData.getPlayers().get(0));
                    sendData(players.get(1), gameData.getPlayers().get(1));
*/
                    if(counter == 0) {
                        gameData.getPlayers().set(0, readData(players.get(0)));
                        gameData.getPlayers().get(1).setOpponent(gameData.getPlayers().get(0));
                        gameData.getPlayers().get(0).setMyTurn(false);
                        gameData.getPlayers().get(1).setMyTurn(true);
                        if(gameData.getPlayers().get(0).getHummus() < 10) {
                            gameData.getPlayers().get(0).setHummus(gameData.getPlayers().get(0).getHummus()+1);
                        }
                        sendData(players.get(1), gameData.getPlayers().get(1));
                        sendData(players.get(0), gameData.getPlayers().get(0));
                        counter = 1;
                    } else {
                        gameData.getPlayers().set(1, readData(players.get(1)));
                        gameData.getPlayers().get(0).setOpponent(gameData.getPlayers().get(1));
                        gameData.getPlayers().get(1).setMyTurn(false);
                        gameData.getPlayers().get(0).setMyTurn(true);
                        if(gameData.getPlayers().get(1).getHummus() < 10) {
                            gameData.getPlayers().get(1).setHummus(gameData.getPlayers().get(1).getHummus()+1);
                        }
                        sendData(players.get(0), gameData.getPlayers().get(0));
                        sendData(players.get(1), gameData.getPlayers().get(1));
                        counter = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
