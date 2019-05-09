package client;

import game.Card;
import game.CardCoordinates;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private ClientTest client;
    private GraphicsContext gc;
    private boolean start;
    private HashMap<Card, CardCoordinates> coordinates;
    private boolean canAttack;
    private Card attackingCard;

    @FXML
    private Label myName, enemyName, myHummus, enemyHummus;

    @FXML
    private Canvas canvas;

    @FXML
    public void handleCanvasClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        if(start) {
            gc.clearRect(0, 0, 1000, 520);
            drawTable();
            for(Card c : coordinates.keySet()) {
                if (coordinates.get(c).getY() >= 420 && coordinates.get(c).getY() <= 500) {
                    for(Card card : client.getPlayerData().getHand()) {
                        if(coordinates.get(card).getX() < e.getX() && coordinates.get(card).getX() + 60 > e.getX()){
                            client.getPlayerData().getHand().remove(card);
                            client.getPlayerData().getPlayedCards().add(card);
                        }
                    }
                }
                if (coordinates.get(c).getY() >= 290 && coordinates.get(c).getY() <= 370) {
                    for(Card card : client.getPlayerData().getPlayedCards()) {
                        if(coordinates.get(card).getX() < e.getX() && coordinates.get(card).getX() + 60 > e.getX()){
                            System.out.println("Green");
                            gc.setStroke(Color.GREEN);
                            gc.strokeRect(coordinates.get(card).getX(), coordinates.get(card).getY(), 60, 80);
                            canAttack = true;
                            attackingCard = card;
                        }
                    }
                }
                if(canAttack) {
                    if (coordinates.get(c).getY() >= 150 && coordinates.get(c).getY() <= 230) {
                        for (Card card : client.getPlayerData().getOpponent().getPlayedCards()) {
                            if (coordinates.get(card).getX() < e.getX() && coordinates.get(card).getX() + 60 > e.getX()) {
                                client.getPlayerData().attack(attackingCard, card);
                                canAttack = false;
                            }
                        }
                    }
                }
            }
            client.sendPlayerData(client.getSocket(), client.getPlayerData());
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case S:
                start = true;
                drawTable();
                enemyName.setText(client.getPlayerData().getOpponent().getName());
                break;
            case D:
                if(start) {
                    if (client.getPlayerData().getDeck().size() != 0) {
                        client.getPlayerData().pullCard();
                        client.sendPlayerData(client.getSocket(), client.getPlayerData());
                    }
                    drawTable();
                }
                break;
        }
    }

    public void setLoginData(String name, String ip, int port){
        Platform.runLater(() -> {
            client = new ClientTest(port, ip, name);
            System.out.println(client.getPlayerData().getName());
            Thread inputThread = new Thread(client);
            inputThread.start();
            myName.setText(client.getPlayerData().getName());
        });
    }

    public void drawCard(Card card, double x, double y){
        card.setCoordinates(new CardCoordinates(x,y));
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
        gc.setFill(Color.BROWN);
        gc.fillRect(100, 100, 800, 320);

        gc.setFill(Color.CRIMSON);
        gc.fillRect(800, 420, 150, 100);
        gc.setFill(Color.BLACK);
        gc.fillText("Deck cards : " + client.getPlayerData().getDeck().size(), 805, 475);
        double counter = 50;
        for(Card c : client.getPlayerData().getOpponent().getHand()){
            drawOpponentHand(counter, 0);
            counter += 70;
        }
        myHummus.setText(client.getPlayerData().getHummus() + "");
        enemyHummus.setText(client.getPlayerData().getOpponent().getHummus() + "");
        drawOpponentTableCards();
        drawMyTableCards();
        drawMyHand();
    }

    public void drawMyHand(){
        double x = 20;
        for(Card c : client.getPlayerData().getHand()){
            drawCard(c, x, 420);
            coordinates.put(c, new CardCoordinates(x, 420));
            x += 70;
        }
    }

    public void drawOpponentHand(double x, double y){
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, 60, 80);
    }

    public void drawOpponentTableCards(){
        double counter = 150;
        for (Card c : client.getPlayerData().getOpponent().getPlayedCards()) {
            if (c.getHealth() > 0) {
                drawCard(c, counter, 150);
                coordinates.put(c, new CardCoordinates(counter, 150));
                counter += 70;
            } else {
                client.getPlayerData().getOpponent().getPlayedCards().remove(c);
            }
        }
    }

    public void drawMyTableCards() {
        double counter = 150;
        for(Card c : client.getPlayerData().getPlayedCards()){
            if(c.getHealth() > 0) {
                drawCard(c, counter, 290);
                coordinates.put(c, new CardCoordinates(counter, 290));
                counter += 70;
            } else {
                client.getPlayerData().getPlayedCards().remove(c);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        coordinates = new HashMap<>();
        start = false;
        canAttack = false;
    }
}
