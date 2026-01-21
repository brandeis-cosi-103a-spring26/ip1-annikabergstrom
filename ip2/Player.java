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
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();

        //TODO: Add 7 Bitcoin, 3 Method cards to drawPile
    }

    public void cleanUp() {
        //TODO: transfer all cards from hand (5) to discard pile
        //TODO: transfer 5 cards from draw pile to hand
        // If draw pile has less than 5 cards, shuffle discard pile into draw pile and continue drawing
        
    }

    @Override
    public String toString() {
        return "Player " + playerNumber;
    }
}

