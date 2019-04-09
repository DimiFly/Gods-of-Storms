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
        run();
    }

    public void test(ServerSocket serverSocket){
        try {
            Socket client = waitForConnection(serverSocket);
            System.out.println(readMessage(client));
            sendMessage(client, "Hey It's the server");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket waitForConnection(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            gameData.getPlayers().add(readData(socket));
            System.out.println(readMessage(socket));
            sendMessage(socket, "Connected to server");
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

    public PlayerData readData(Socket socket){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return (PlayerData) objectInputStream.readObject();
        } catch (Exception e) {
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
}
