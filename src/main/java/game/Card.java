package game;

import java.io.Serializable;

public class Card implements Serializable {

    private String name;
    private int cost;
    private int health;
    private int damage;
    private boolean inUse;
    private CardCoordinates coordinates;

    public Card(String name, int cost, int health, int damage) {
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.damage = damage;
        this.inUse = false;
        coordinates = new CardCoordinates(0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public CardCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CardCoordinates coordinates) {
        this.coordinates = coordinates;
    }
}
