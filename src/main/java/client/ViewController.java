package client;

import javafx.application.Platform;
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

    @FXML
    private TextField username;
    @FXML
    private TextField port;
    @FXML
    private TextField ip;

    @FXML
    public void handlePlayAction(ActionEvent e) {
        final Node node = (Node) e.getSource();
        Platform.runLater(() -> {
                    if (!username.equals("") && !port.equals("") && !port.equals("")) {
                        openGameStage();
                        node.getScene().getWindow().hide();
                    }

                }
        );
    }

    public void openGameStage() {
        Platform.runLater(() -> {
                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
                        root = loader.load();
                        System.out.println(ip.getText());
                        GameController gameController = loader.getController();
                        gameController.setLoginData(username.getText(), ip.getText(), Integer.parseInt(port.getText()));
                        Stage stage = new Stage();

                        stage.setTitle("Gods of Storms");
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //openGameStage();
    }
}
