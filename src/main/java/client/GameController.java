package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private ClientTest client;

    @FXML
    private Label myName, enemyName;

    public void setLoginData(String name, String ip, int port){
        client = new ClientTest(port, ip, name);
        Thread inputThread = new Thread(client);
        inputThread.start();
        myName.setText(client.getPlayerData().getName());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
