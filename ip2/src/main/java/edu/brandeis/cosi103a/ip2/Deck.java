package edu.brandeis.cosi103a.ip2;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the supply of purchasable cards. Manages the inventory of automation 
 * cards and cryptocurrency cards, along with their costs and values.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
 */
public class Deck {
    private AutomationCard automationCards;
    private CryptocurrencyCard cryptocurrencyCards;
    
    /**
     * Constructs a new Deck with the starting supply of cards.
     */
    public Deck() {
        // Initialize card counts: Method(14), Module(8), Framework(8)
        automationCards = new AutomationCard(14, 8, 8);
        // Initialize card counts: Bitcoin(60), Ethereum(40), Dogecoin(30)
        cryptocurrencyCards = new CryptocurrencyCard(60, 40, 30);
    }

    /**
     * Gets the number of Framework cards remaining in the supply.
     * Used to determine when the game should end.
     * @return the number of Framework cards available
     */
    public int getNumberFrameworkCards() {
        return automationCards.getNumberFrameworkCards();
    }
    
    /**
     * Attempts to purchase a card from the supply.
     * Checks if the card is available and if the player has enough coins.
     * Decrements the card count in the supply if the purchase is successful.
     * 
     * @param cardName the name of the card to purchase
     * @param availableCoins the number of coins the player has available
     * @return the purchased Card object, or null if the purchase cannot be made
     */
    public Card buyCard(String cardName, int availableCoins) {
        // Try to buy automation card
        if (automationCards.getCount(cardName) > 0) {
            int cost = automationCards.getCost(cardName);
            if (cost >= 0 && availableCoins >= cost) {
                automationCards.decrementCount(cardName);
                return new Card(cost, automationCards.getValue(cardName), cardName);
            }
        }
        
        // Try to buy cryptocurrency card
        if (cryptocurrencyCards.getCount(cardName) > 0) {
            int cost = cryptocurrencyCards.getCost(cardName);
            if (cost >= 0 && availableCoins >= cost) {
                cryptocurrencyCards.decrementCount(cardName);
                return new Card(cost, cryptocurrencyCards.getValue(cardName), cardName);
            }
        }
        
        return null; // Cannot buy
    }
    
    /**
     * Determines the best card a player can afford with their available coins.
     * Prioritizes automation cards over cryptocurrency cards for strategic value.
     * Selects the most expensive affordable card to maximize value.
     * 
     * @param availableCoins the number of coins the player has available
     * @return the name of the best affordable card, or null if no cards can be purchased
     */
    public String getBestAffordableCard(int availableCoins) {
        String bestCard = null;
        int bestCost = -1;
        
        // Check automation cards (prioritize these for points)
        String[] automationCardNames = {"Method", "Module", "Framework"};
        for (String cardName : automationCardNames) {
            if (automationCards.getCount(cardName) > 0) {
                int cost = automationCards.getCost(cardName);
                if (cost <= availableCoins && cost > bestCost) {
                    bestCard = cardName;
                    bestCost = cost;
                }
            }
        }
        
        // Check cryptocurrency cards if no automation card found
        if (bestCard == null) {
            String[] cryptocurrencyCardNames = {"Bitcoin", "Ethereum", "Dogecoin"};
            for (String cardName : cryptocurrencyCardNames) {
                if (cryptocurrencyCards.getCount(cardName) > 0) {
                    int cost = cryptocurrencyCards.getCost(cardName);
                    if (cost <= availableCoins && cost > bestCost) {
                        bestCard = cardName;
                        bestCost = cost;
                    }
                }
            }
        }
        
        return bestCard;
    }
    
    /**
     * Gets the cost of a specific card by name.
     * 
     * @param cardName the name of the card
     * @return the cost of the card in coins, or -1 if the card is not found
     */
    public int getCardCost(String cardName) {
        int cost = automationCards.getCost(cardName);
        if (cost >= 0) {
            return cost;
        }
        cost = cryptocurrencyCards.getCost(cardName);
        if (cost >= 0) {
            return cost;
        }
        return -1;
    }
}
