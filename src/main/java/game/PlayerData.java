package game;

import java.util.ArrayList;

public class PlayerData {

    private String name;
    private int hummus;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public PlayerData(String name, ArrayList<Card> deck) {
        this.name = name;
        this.hummus = 1;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHummus() {
        return hummus;
    }

    public void setHummus(int hummus) {
        this.hummus = hummus;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}
