package client;

import client.packets.AddPlayerPacket;
import client.packets.RemovePlayerPacket;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements Runnable {

    private String host;
    private int port;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running = false;
    private EventListener listener;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            listener = new EventListener();
            AddPlayerPacket packet = new AddPlayerPacket();
            sendObject(packet);
            new Thread(this).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
        running = false;
        RemovePlayerPacket packet = new RemovePlayerPacket();
        sendObject(packet);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object packet) {
        try {
            out.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            running = true;

            while(running) {
                try {
                    Object data = in.readObject();
                    listener.receive(data);
                } catch (SocketException e) {
                    close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
