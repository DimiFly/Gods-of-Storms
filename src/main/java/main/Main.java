package main;

import servertest.ClientTest;
import servertest.ServerTest;

public class Main {

    public static void main(String[] args) {
        //ServerTest serverTest = new ServerTest(4200);
        ClientTest clientTest = new ClientTest(4200, "localhost", "Grom2");

        clientTest.connectToServer();
    }
}
