package edu.brandeis.cosi103a.ip2;

/**
 * Represents a card in the cryptocurrency card game. All cards have a cost, value, and type.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
 */
public class Card {
    private int cost;
    private int value;
    private String type;

    /**
     * Constructs a new Card with the specified properties.
     * @param cost the number of coins required to purchase this card
     * @param value the value of this card (coins for cryptocurrency, points for automation)
     * @param type the name/type of this card
     */
    public Card(int cost, int value, String type) {
        this.cost = cost;
        this.value = value;
        this.type = type;
    }

    /**
     * Gets the cost of this card.
     * @return the number of coins required to purchase this card
     */
    public int getCost() {
        return cost;
    }
    
    /**
     * Gets the value of this card.
     * @return the value of this card
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Gets the type/name of this card.
     * @return the type of this card (e.g., "Bitcoin", "Method", "Framework")
     */
    public String getType() {
        return type;
    }
}
