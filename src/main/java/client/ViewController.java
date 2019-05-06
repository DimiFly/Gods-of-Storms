package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ViewController implements Initializable {

    private ClientTest client;

    @FXML
    private TextField username;
    @FXML
    private TextField port;
    @FXML
    private TextField ip;
    @FXML
    private Label myName;
    @FXML
    private Label enemyName;
    @FXML
    private Canvas canvas;

    @FXML
    public void handlePlayAction(ActionEvent e) {
        if(!username.equals(null) && !port.equals(null) && !port.equals(null)) {
            ((Node) (e.getSource())).getScene().getWindow().hide();
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(1);
            //client = new ClientTest(4200, "192.168.1.223", "Noa");
            client = new ClientTest(Integer.parseInt(port.getText()), ip.getText(), username.getText());
            System.out.println(2);
            myName.setText(client.getPlayerData().getName());
            enemyName.setText(client.getPlayerData().getOpponent().getName());
            System.out.println(3);
        }
    }

    public void openGameStage(){
        Parent root = null;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("/Game.fxml"));
            stage.setTitle("Gods of Storms");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //openGameStage();
    }
}
