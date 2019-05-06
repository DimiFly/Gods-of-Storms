package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class PlayerData implements Serializable {

    private String name;
    private int hummus;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> playedCards;
    private PlayerData opponent;

    public PlayerData(String name) {
        this.name = name;
        this.hummus = 1;
        this.deck = new ArrayList<Card>();
        this.hand = new ArrayList<Card>();
        this.playedCards = new ArrayList<Card>();
    }

    public void pullCard() {
        Random r = new Random();
        int index = r.nextInt(deck.size());
        hand.add(deck.get(index));
        deck.remove(index);
    }

    public void playCard(Card card) {
        playedCards.add(card);
        hand.remove(card);
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

    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(ArrayList<Card> playedCards) {
        this.playedCards = playedCards;
    }

    public PlayerData getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerData opponent) {
        this.opponent = opponent;
    }
}
