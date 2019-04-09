package game;

import java.io.Serializable;

public class Card implements Serializable {

    private int id;
    private String name;
    private int cost;
    private int health;
    private int damage;

    public Card(int id, String name, int cost, int health, int damage) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
