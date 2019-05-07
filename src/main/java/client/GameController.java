package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private ClientTest client;
    private GraphicsContext gc;

    @FXML
    private Label myName, enemyName;

    @FXML
    private Canvas canvas;

    @FXML
    public void handleCanvasClicked(MouseEvent e) {

    }

    public void setLoginData(String name, String ip, int port){
        client = new ClientTest(port, ip, name);
        Thread inputThread = new Thread(client);
        inputThread.start();
        myName.setText(client.getPlayerData().getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        while (client.getPlayerData().getOpponent().equals(null)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enemyName.setText(client.getPlayerData().getOpponent().getName());
    }
}
