package main;

import client.Client;
import multiplayer.ServerConnector;

public class Main {

    public static void main(String[] args) {
        ServerConnector server = new ServerConnector(4200, 10);
        Client client = new Client("192.168.106.199", 4200);

        server.start();

        client.connect();
    }
}
