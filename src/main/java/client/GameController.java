package client;

import game.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        enemyName.setText(client.getPlayerData().getOpponent().getName());
        double counter = 20;
        drawTable();
        for(Card c : client.getPlayerData().getDeck()){
            drawCard(c, counter, 420);
            counter += 70;
        }
        if (e.getY() >= 420) {

        }
    }

    public void setLoginData(String name, String ip, int port){
        client = new ClientTest(port, ip, name);
        Thread inputThread = new Thread(client);
        inputThread.start();
        myName.setText(client.getPlayerData().getName());
    }

    public void drawCard(Card card, double x, double y){
        String[] name = card.getName().split(",");
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, 60, 80);
        gc.setFill(Color.BURLYWOOD);
        gc.fillOval(x + 18,y + 20,26,26);
        gc.setFill(Color.GRAY);
        gc.fillOval(x + 3,y + 50,25,25);
        gc.setFill(Color.TOMATO);
        gc.fillOval(x + 32,y + 50,25,25);
        gc.setFill(Color.BLACK);
        gc.fillText(name[0], x+5, y+15, 50);
        gc.fillText(card.getDamage() + "", x + 10, y + 65);
        gc.fillText(card.getHealth() + "", x + 40, y + 65);
        gc.fillText(card.getCost() + "", x + 26, y + 35);
    }

    public void drawTable(){
        gc.clearRect(0,0,1000,520);
        gc.setFill(Color.BROWN);
        gc.fillRect(100, 100, 800, 320);

        gc.setFill(Color.CRIMSON);
        gc.fillRect(800, 420, 100, 100);
        gc.setFill(Color.BLACK);
        gc.fillText("Deck cards : " + client.getPlayerData().getDeck().size(), 805, 475);
        double counter = 50;
        for(Card c : client.getPlayerData().getOpponent().getHand()){
            drawOpponentHand(counter, 0);
            counter += 70;
        }
    }

    public void drawOpponentHand(double x, double y){
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, 60, 80);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
    }
}
