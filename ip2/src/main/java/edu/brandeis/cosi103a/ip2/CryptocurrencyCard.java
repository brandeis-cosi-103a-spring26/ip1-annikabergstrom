package edu.brandeis.cosi103a.ip2;

/**
 * Represents a cryptocurrency card in the cryptocurrency card game.
 * Cryptocurrency cards can be played to generate coins for purchasing other cards.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
 */
public class CryptocurrencyCard {
    private int bitcoinNum;
    private int ethereumNum;
    private int dogecoinNum;
    
    /**
     * Creates a new CryptocurrencyCard with the specified properties.
     * @param bitcoinNum Value or cost of Bitcoin cards
     * @param ethereumNum Value or cost of Ethereum cards
     * @param dogecoinNum Value or cost of Dogecoin cards
     */
    public CryptocurrencyCard(int bitcoinNum, int ethereumNum, int dogecoinNum) {
        this.bitcoinNum = bitcoinNum;
        this.ethereumNum = ethereumNum;
        this.dogecoinNum = dogecoinNum;
    }
    
    /**
     * Gets the value or count of the specified card type
     * @param cardName name of card type. "Bitcoin", "Ethereum", "Dogecoin"
     * @return value or count of that card type . 0 else
     */
    public int getCount(String cardName) {
        switch (cardName) {
            case "Bitcoin": return bitcoinNum;
            case "Ethereum": return ethereumNum;
            case "Dogecoin": return dogecoinNum;
            default: return 0;
        }
    }
    
    /**
     * Drecrements the count of the specified card type by 1
     * @param cardName name of card type
     */
    public void decrementCount(String cardName) {
        switch (cardName) {
            case "Bitcoin": 
                if (bitcoinNum > 0) bitcoinNum--;
                break;
            case "Ethereum": 
                if (ethereumNum > 0) ethereumNum--;
                break;
            case "Dogecoin": 
                if (dogecoinNum > 0) dogecoinNum--;
                break;
        }
    }
    
    /**
     * Returns the cost of the specified card type. If not valid, return -1
     * @param cardName String of card name
     * @return cost of the card type
     */
    public int getCost(String cardName) {
        switch (cardName) {
            case "Bitcoin": return 0;
            case "Ethereum": return 3;
            case "Dogecoin": return 6;
            default: return -1;
        }
    }
    
    /**
     * Returns the value of the specified card type. If not valid, return -1
     * @param cardName name of the card
     * @return value of the card type
     */
    public int getValue(String cardName) {
        switch (cardName) {
            case "Bitcoin": return 1;
            case "Ethereum": return 2;
            case "Dogecoin": return 3;
            default: return -1;
        }
    }
}