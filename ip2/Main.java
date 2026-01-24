public class Main {
    
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Deck decks = new Deck();

        java.util.Random rand = new java.util.Random();
        Player[] players = new Player[]{player1, player2};
        int current = rand.nextInt(2);

        while (decks.getNumberFrameworkCards() > 0) {
            Player p = players[current];
            // Buy phase: play all cryptocurrency cards in hand for money
            int money = p.playCryptocurrencies();
            Card purchased = null;

            // Prefer automation cards (Framework > Module > Method)
            if (money >= 8 && decks.getAutomationCount("Framework") > 0) {
                purchased = decks.buyAutomationCard("Framework");
            } else if (money >= 5 && decks.getAutomationCount("Module") > 0) {
                purchased = decks.buyAutomationCard("Module");
            } else if (money >= 2 && decks.getAutomationCount("Method") > 0) {
                purchased = decks.buyAutomationCard("Method");
            } else {
                // If no automation affordable, buy cryptocurrency (best value affordable)
                if (money >= 6 && decks.getCryptocurrencyCount("Dogecoin") > 0) {
                    purchased = decks.buyCryptocurrencyCard("Dogecoin");
                } else if (money >= 3 && decks.getCryptocurrencyCount("Etherium") > 0) {
                    purchased = decks.buyCryptocurrencyCard("Etherium");
                } else if (decks.getCryptocurrencyCount("Bitcoin") > 0) {
                    purchased = decks.buyCryptocurrencyCard("Bitcoin");
                }
            }

            if (purchased != null) {
                p.addToDiscard(purchased);
            }

            // Cleanup phase
            p.cleanUp();

            if (decks.getNumberFrameworkCards() <= 0) break;
            current = 1 - current; // alternate player
        }

        // Game end: compute APs and announce winner
        int ap1 = player1.totalAutomationPoints();
        int ap2 = player2.totalAutomationPoints();
        System.out.println("Game over. Player 1 AP: " + ap1 + " Player 2 AP: " + ap2);
        if (ap1 > ap2) System.out.println("Player 1 wins!");
        else if (ap2 > ap1) System.out.println("Player 2 wins!");
        else System.out.println("Tie!");


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
