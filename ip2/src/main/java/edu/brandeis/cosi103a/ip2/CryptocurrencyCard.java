package edu.brandeis.cosi103a.ip2;

/**
 * Represents a cryptocurrency card in the cryptocurrency card game.
 * Cryptocurrency cards can be played to generate coins for purchasing other cards.
 */
public class CryptocurrencyCard {
    private int bitcoinNum;
    private int ethereumNum;
    private int dogecoinNum;
    
    public CryptocurrencyCard(int bitcoinNum, int ethereumNum, int dogecoinNum) {
        this.bitcoinNum = bitcoinNum;
        this.ethereumNum = ethereumNum;
        this.dogecoinNum = dogecoinNum;
    }
    
    public int getCount(String cardName) {
        switch (cardName) {
            case "Bitcoin": return bitcoinNum;
            case "Ethereum": return ethereumNum;
            case "Dogecoin": return dogecoinNum;
            default: return 0;
        }
    }
    
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
    
    public int getCost(String cardName) {
        switch (cardName) {
            case "Bitcoin": return 0;
            case "Ethereum": return 3;
            case "Dogecoin": return 6;
            default: return -1;
        }
    }
    
    public int getValue(String cardName) {
        switch (cardName) {
            case "Bitcoin": return 1;
            case "Ethereum": return 2;
            case "Dogecoin": return 3;
            default: return -1;
        }
    }
}