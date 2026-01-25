import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Deck decks = new Deck();

        while (decks.getNumberFrameworkCards() > 0) {
            // Player 1's turn
            // Buy phase: TODO
            player1.cleanUp();
            if (decks.getNumberFrameworkCards() <= 0) {
                break;
            }
            // Player 2's turn
            // Buy phase: TODO
            player2.cleanUp();
        }


        /**
         * TODO: 
         * While automationCards.get("Framework") > 0 do     //TODO: alternate players
         *     Buy phase: Buy up to 1 card using value of cryptocurrency cards in hand
         *     Cleanup phase: Discard all cards from hand to discard pile
         *                  Draw phase: Draw 5 cards from draw pile to hand
         * }
         */

    }


}
