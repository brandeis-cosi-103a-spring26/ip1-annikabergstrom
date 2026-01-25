package edu.brandeis.cosi103a.ip2;

public abstract class Card {
    private int cost;
    private int value;
    private String type;

    public Card(int cost, int value, String type) {
        this.cost = cost;
        this.value = value;
        this.type = type;
    }

    public int getCost() {
        return cost;
    }
    public int getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
}
