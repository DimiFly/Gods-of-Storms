package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import client.ClientTest;

import java.io.IOException;
import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:src/main/resources/Menu.fxml"));
        primaryStage.setTitle("Gods of Storms");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    public static void main(String[] args) {
        //ServerTest serverTest = new ServerTest(4200);
        //ClientTest clientTest = new ClientTest(4200, "localhost", "Grom1");
        launch(args);
        /*ServerTest serverTest = new ServerTest(4200);
        ClientTest clientTest = new ClientTest(4200, "192.168.107.109", "Grom1");

        clientTest.connectToServer();
        /serverTest.test();*/
        //clientTest.connectToServer();
    }
}