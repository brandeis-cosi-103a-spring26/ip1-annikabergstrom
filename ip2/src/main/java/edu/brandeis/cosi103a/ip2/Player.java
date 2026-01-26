package edu.brandeis.cosi103a.ip2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a player in the cryptocurrency card game.
 * Each player has their own deck consisting of a draw pile, hand, and discard pile.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
 */
public class Player {
    private int playerNumber;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    /**
     * Constructs a new Player with the specified player number.
     * Initializes the player's deck with 7 Bitcoin cards and 3 Method cards,
     * shuffles the draw pile, and draws an initial hand of 5 cards.
     * 
     * @param playerNumber the unique identifier for this player
     */
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        initializePiles();
    }

    /**
     * Gets the player's number. Used in printing statistics in Main
     * @return player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Initializes the player's deck with starting cards.
     */
    private void initializePiles() {
        hand = new ArrayList<>();
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();

        // Add 7 Bitcoin, 3 Method cards to drawPile
        for (int i = 0; i < 7; i++) {
            drawPile.add(new Card(0, 1, "Bitcoin"));
        }
        for (int i = 0; i < 3; i++) {
            drawPile.add(new Card(2, 1, "Method"));
        }
        Collections.shuffle(drawPile); // Shuffle draw pile

        this.drawCard(5); // Draw initial hand of 5 cards
    }

    /**
     * Draws the specified number of cards from the draw pile to the hand.
     * @param num the number of cards to draw
     */
    private void drawCard(int num) {
        if (num <= 0) return;
        for (int i = 0; i < num; i++) {
            if (drawPile.isEmpty()) {
                reshuffleDiscardIntoDraw();
            }
            if (!drawPile.isEmpty()) { 
                hand.add(drawPile.remove(drawPile.size() - 1));
            }
        }
    }

    /**
     * Reshuffles discard pile and adds all cards back to the draw pile.
     */
    private void reshuffleDiscardIntoDraw() {
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }

    /**
     * Performs the cleanup phase of a turn (discard and draw new)
     */
    public void cleanUp() {
        // transfer all cards from hand (5) to discard pile
        discardPile.addAll(hand);
        hand.clear();

        // transfer 5 cards from draw pile to hand
        drawCard(5);
    }

    /**
     * Calculates the total value of all cryptocurrency cards in the player's hand.
     * @return the total coin value of cryptocurrency cards in hand
     */
    public int playCryptocurrencies() {
        int total = 0;
        for (Card c : hand) {
            String type = c.getType();
            if (type.equals("Bitcoin") || type.equals("Ethereum") || type.equals("Dogecoin")) {
                total += c.getValue();
            }
        }
        return total;
    }

    /**
     * Adds a card to the discard pile.
     * @param c the card to add to the discard pile (null values are ignored)
     */
    public void addToDiscard(Card c) {
        if (c != null) discardPile.add(c);
    }

    /**
     * Calculates the total Automation Points across all of the player's cards (hand, draw, discard)
     * @return the total Automation Points for determining the winner
     */
    public int totalAutomationPoints() {
        int total = 0;
        for (Card c : drawPile) {
            String type = c.getType();
            if (type.equals("Method") || type.equals("Module") || type.equals("Framework")) {
                total += c.getValue();
            }
        }
        for (Card c : hand) {
            String type = c.getType();
            if (type.equals("Method") || type.equals("Module") || type.equals("Framework")) {
                total += c.getValue();
            }
        }
        for (Card c : discardPile) {
            String type = c.getType();
            if (type.equals("Method") || type.equals("Module") || type.equals("Framework")) {
                total += c.getValue();
            }
        }
        return total;
    }

    /**
     * Performs the buy phase of a turn.
     * @param deck the game deck from which to purchase cards
     * @param roundNumber the current round number for strategy decisions
     * @return the name of the card purchased, or null if no purchase was made
     */
    public String buyPhase(Deck deck, int roundNumber) {
        // Play all cryptocurrencies to get total coins
        int totalCoins = playCryptocurrencies();
        
        // Get best affordable card and buy it
        String cardToBuy = deck.getBestAffordableCard(totalCoins, roundNumber);
        if (cardToBuy != null) {
            Card boughtCard = deck.buyCard(cardToBuy, totalCoins);
            if (boughtCard != null) {
                addToDiscard(boughtCard);
                return cardToBuy;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Player " + playerNumber;
    }
}

