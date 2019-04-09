package game;

import java.util.ArrayList;

public class GameData {

    private ArrayList<PlayerData> players;
    private ArrayList<Card> allCards;

    public GameData() {
        this.players = new ArrayList<PlayerData>();
        this.allCards = new ArrayList<Card>();
        addAllCards();
    }

    public void addAllCards(){
        allCards.add(new Card(1, "Dimitri", 2, 3, 15));
        allCards.add(new Card(2, "Noa", 9, 65523, 1));
        allCards.add(new Card(3, "Trump", 3, 4, 5));
        allCards.add(new Card(4, "Yang", 1, 69, 100));
    }

    public ArrayList<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerData> players) {
        this.players = players;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void setAllCards(ArrayList<Card> allCards) {
        this.allCards = allCards;
    }
}
