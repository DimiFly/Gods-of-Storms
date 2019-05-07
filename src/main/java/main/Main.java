package main;
import client.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.ClientTest;
import javafx.stage.StageStyle;
import server.ServerTest;

import java.io.IOException;
import java.net.URL;


public class Main extends Application {

    ViewController viewController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        viewController = new ViewController();
        Parent root = FXMLLoader.load(new URL("file:src/main/resources/Menu.fxml"));
        primaryStage.setTitle("Gods of Storms");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

/*
        Stage stage = new Stage();
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        stage.setTitle("Gods of Storms");
        stage.setScene(new Scene(anotherRoot));
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();*/


    }

    public static void main(String[] args) {
        //ServerTest serverTest = new ServerTest(4200);
        //Thread thread = new Thread(new ServerTest(4200));
        //thread.start();
        //ClientTest clientTest = new ClientTest(4200, "localhost", "Grom1");
        launch(args);

    }
}