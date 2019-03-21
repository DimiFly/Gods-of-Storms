package main;

import servertest.ClientTest;
import servertest.ServerTest;

public class Main {

    public static void main(String[] args) {
        ServerTest serverTest = new ServerTest(4200);
        //ClientTest clientTest = new ClientTest(4200, "192.168.106.5");

        serverTest.test();
        //clientTest.test();
    }
}
