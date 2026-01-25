package edu.brandeis.cosi103a.ip2;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private int playerNumber;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        initializePiles();
    }

    private void initializePiles() {
        hand = new ArrayList<>();
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();

        // Add 7 Bitcoin, 3 Method cards to drawPile
        for (int i = 0; i < 7; i++) {
            drawPile.add(new CryptocurrencyCard(0, 1, "Bitcoin"));
        }
        for (int i = 0; i < 3; i++) {
            drawPile.add(new AutomationCard(2, 1, "Method"));
        }
        Collections.shuffle(drawPile);

        // Draw initial hand of 5 cards
        for (int i = 0; i < 5; i++) {
            drawCard();
        }
    }

    private void drawCard() {
        drawCard(1);
    }

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


    private void reshuffleDiscardIntoDraw() {
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }

    public void cleanUp() {
        // transfer all cards from hand (5) to discard pile
        discardPile.addAll(hand);
        hand.clear();

        // transfer 5 cards from draw pile to hand
        drawCard(5);
    }

    public int playCryptocurrencies() {
        int total = 0;
        for (Card c : hand) {
            if (c instanceof CryptocurrencyCard) {
                total += c.getValue();
            }
        }
        return total;
    }

    public void addToDiscard(Card c) {
        if (c != null) discardPile.add(c);
    }

    public int totalAutomationPoints() {
        int total = 0;
        for (Card c : drawPile) {
            if (c instanceof AutomationCard) total += c.getValue();
        }
        for (Card c : hand) {
            if (c instanceof AutomationCard) total += c.getValue();
        }
        for (Card c : discardPile) {
            if (c instanceof AutomationCard) total += c.getValue();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Player " + playerNumber;
    }
}

