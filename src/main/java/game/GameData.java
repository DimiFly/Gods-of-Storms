package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameData implements Serializable {

    private ArrayList<PlayerData> players;
    private ArrayList<Card> allCards;

    public GameData() {
        this.players = new ArrayList<PlayerData>();
        this.allCards = new ArrayList<Card>();
        addAllCards();
    }

    public void addAllCards(){
        allCards.add(new Card("Yteus, Goddess of Work", 4, 4, 4));
        allCards.add(new Card("Yteus, Goddess of Work", 4, 4, 4));
        allCards.add(new Card("Yteus, Goddess of Work", 4, 4, 4));
        allCards.add(new Card("Evmos, Goddess of Charity", 1, 1, 2));
        allCards.add(new Card("Evmos, Goddess of Charity", 1, 1, 2));
        allCards.add(new Card("Evmos, Goddess of Charity", 1, 1, 2));
        allCards.add(new Card("Evmos, Goddess of Charity", 1, 1, 2));
        allCards.add(new Card("Evmos, Goddess of Charity", 1, 1, 2));
        allCards.add(new Card("Phosyn, Goddess of the Rivers", 3, 4, 2));
        allCards.add(new Card("Phosyn, Goddess of the Rivers", 3, 4, 2));
        allCards.add(new Card("Phosyn, Goddess of the Rivers", 3, 4, 2));
        allCards.add(new Card("Phosyn, Goddess of the Rivers", 3, 4, 2));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Gessin, Goddess of Earth", 3, 2, 3));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Vaeyary, Goddess of Fame", 1, 1, 0));
        allCards.add(new Card("Kaxaris, Goddess of the Sun", 6, 6, 7));
        allCards.add(new Card("Kaxaris, Goddess of the Sun", 6, 6, 7));
        allCards.add(new Card("Quoraura, Goddess of Justice", 6, 8, 4));
        allCards.add(new Card("Quoraura, Goddess of Justice", 6, 8, 4));
        allCards.add(new Card("Vaasis, Goddess of Freedom", 3, 5, 1));
        allCards.add(new Card("Vaasis, Goddess of Freedom", 3, 5, 1));
        allCards.add(new Card("Vaasis, Goddess of Freedom", 3, 5, 1));
        allCards.add(new Card("Vaasis, Goddess of Freedom", 3, 5, 1));
        allCards.add(new Card("Kasva, Goddess of Nature", 5, 6, 4));
        allCards.add(new Card("Kasva, Goddess of Nature", 5, 6, 4));
        allCards.add(new Card("Kasva, Goddess of Nature", 5, 6, 4));
        allCards.add(new Card("Oslan, God of Life & Death", 9, 9, 9));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Dudon, God of Dreams", 2, 1, 2));
        allCards.add(new Card("Qomis, God  of Magic", 4, 2, 6));
        allCards.add(new Card("Qomis, God  of Magic", 4, 2, 6));
        allCards.add(new Card("Qomis, God  of Magic", 4, 2, 6));
        allCards.add(new Card("Qomis, God  of Magic", 4, 2, 6));
        allCards.add(new Card("Omsus, God  of Charity", 3, 5, 1));
        allCards.add(new Card("Omsus, God  of Charity", 3, 5, 1));
        allCards.add(new Card("Omsus, God  of Charity", 3, 5, 1));
        allCards.add(new Card("Omsus, God  of Charity", 3, 5, 1));
        allCards.add(new Card("Bozotl, God of the Ostracized", 6, 8, 4));
        allCards.add(new Card("Bozotl, God of the Ostracized", 6, 8, 4));
        allCards.add(new Card("Ualdir, God of Honesty", 6, 9, 2));
        allCards.add(new Card("Ualdir, God of Honesty", 6, 9, 2));
        allCards.add(new Card("Ualdir, God of Honesty", 6, 9, 2));
        allCards.add(new Card("Kheysus, God of the Ocean", 7, 7, 8));
        allCards.add(new Card("Kheysus, God of the Ocean", 7, 7, 8));
        allCards.add(new Card("Yxzotl, God of Wine", 3, 4, 2));
        allCards.add(new Card("Yxzotl, God of Wine", 3, 4, 2));
        allCards.add(new Card("Yxzotl, God of Wine", 3, 4, 2));
        allCards.add(new Card("Yxzotl, God of Wine", 3, 4, 2));
        allCards.add(new Card("Yxzotl, God of Wine", 3, 4, 2));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Lolotl, God of Autumn", 1, 1, 0));
        allCards.add(new Card("Dhudur, God of Riddles", 3, 4, 2));
        allCards.add(new Card("Dhudur, God of Riddles", 3, 4, 2));
        allCards.add(new Card("Ophin, Goddess of Storms", 10, 10, 10));
    }

    public void drawCards(){
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            int index = r.nextInt(80);
            if(!allCards.get(index).isInUse()) {
                allCards.get(index).setInUse(true);
                players.get(0).getDeck().add(allCards.get(index));
            } else {
                i--;
            }
        }

        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            int index = r.nextInt(80);
            if(!allCards.get(index).isInUse()) {
                allCards.get(index).setInUse(true);
                players.get(1).getDeck().add(allCards.get(index));
            } else {
                i--;
            }
        }
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
