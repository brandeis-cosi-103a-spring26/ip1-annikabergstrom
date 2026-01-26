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
     * Creates the draw pile with 7 Bitcoin cards and 3 Method cards,
     * shuffles it, and draws the initial hand of 5 cards.
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
     * If the draw pile becomes empty, the discard pile is shuffled and becomes the new draw pile.
     * 
     * @param num the number of cards to draw
     */
    private void drawCard(int num) {
        if (num <= 0) return;
        for (int i = 0; i < num; i++) {
            if (drawPile.isEmpty()) {
                reshuffleDiscardIntoDraw();
            }
            if (!drawPile.isEmpty()) { // TODO: do I need a separate if check here or can I just put line below as next line?
                hand.add(drawPile.remove(drawPile.size() - 1));
            }
        }
    }

    /**
     * Reshuffles the discard pile and adds all cards back to the draw pile.
     * Clears the discard pile after transferring the cards.
     */
    private void reshuffleDiscardIntoDraw() {
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }

    /**
     * Performs the cleanup phase of a turn.
     * Discards all cards from the hand to the discard pile,
     * then draws 5 new cards from the draw pile.
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
     * 
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
     * 
     * @param c the card to add to the discard pile (null values are ignored)
     */
    public void addToDiscard(Card c) {
        if (c != null) discardPile.add(c);
    }

    /**
     * Calculates the total Automation Points across all of the player's cards.
     * Sums the values of all AutomationCard instances in the draw pile, hand, and discard pile.
     * 
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
     * Calculates total available coins from cryptocurrency cards in hand,
     * determines the best affordable card, purchases it, and adds it to the discard pile.
     * 
     * @param deck the game deck from which to purchase cards
     * @return the name of the card purchased, or null if no purchase was made
     */
    public String buyPhase(Deck deck) {
        // Play all cryptocurrencies to get total coins
        int totalCoins = playCryptocurrencies();
        
        // Get best affordable card and buy it
        String cardToBuy = deck.getBestAffordableCard(totalCoins);
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

