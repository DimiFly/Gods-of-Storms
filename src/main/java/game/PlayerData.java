package game;

import java.util.ArrayList;

public class PlayerData {

    private String name;
    private int mana;
    private int health;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public PlayerData(String name, ArrayList<Card> deck) {
        this.name = name;
        this.mana = 1;
        this.health = 30;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
