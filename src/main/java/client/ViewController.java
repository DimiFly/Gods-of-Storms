package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    private ClientTest client;

    @FXML
    private TextField username;
    private TextField port;
    private TextField ip;

    @FXML
    public void handlePlayAction(ActionEvent e) {
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


        ((Node)(e.getSource())).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
