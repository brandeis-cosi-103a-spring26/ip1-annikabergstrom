package edu.brandeis.cosi103a.ip2;
import java.util.Random;

/**
 * Main class for running the cryptocurrency card game.
 * Manages the game loop, alternating turns between two automated players,
 * and determining the winner based on total Automation Points.
 */
public class Main {
    
    /**
     * Main method for cryptocurrency card game where 2 automated players compete.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Deck decks = new Deck();

        // Randomly choose starting player
        Random rand = new Random();
        boolean player1Starts = rand.nextBoolean();
        System.out.println("Game started! " + (player1Starts ? "Player 1" : "Player 2") + " goes first.\n");
        
        int turnCount = 0;
        while (decks.getNumberFrameworkCards() > 0) {
            turnCount++;
            System.out.println("ROUND " + turnCount);
            
            if (player1Starts) {
                // Player 1's turn
                String card1 = player1.buyPhase(decks, turnCount);
                player1.cleanUp();
                System.out.println("-Player 1");
                System.out.println("  ACTION: Bought " + (card1 != null ? card1 : "nothing"));
                System.out.println("  STATUS: Total AP is now " + player1.totalAutomationPoints());
                
                if (decks.getNumberFrameworkCards() <= 0) {
                    break;
                }
                
                // Player 2's turn
                String card2 = player2.buyPhase(decks, turnCount);
                player2.cleanUp();
                System.out.println("-Player 2");
                System.out.println("  ACTION: Bought " + (card2 != null ? card2 : "nothing"));
                System.out.println("  STATUS: Total AP is now " + player2.totalAutomationPoints());
            } else {
                // Player 2's turn
                String card2 = player2.buyPhase(decks, turnCount);
                player2.cleanUp();
                System.out.println("-Player 2");
                System.out.println("  ACTION: Bought " + (card2 != null ? card2 : "nothing"));
                System.out.println("  STATUS: Total AP is now " + player2.totalAutomationPoints());
                
                if (decks.getNumberFrameworkCards() <= 0) {
                    break;
                }
                
                // Player 1's turn
                String card1 = player1.buyPhase(decks, turnCount);
                player1.cleanUp();
                System.out.println("-Player 1");
                System.out.println("  ACTION: Bought " + (card1 != null ? card1 : "nothing"));
                System.out.println("  STATUS: Total AP is now " + player1.totalAutomationPoints());
            }
            System.out.println();
        }

        // Game ended - determine winner
        System.out.println("\nGame Over! All Framework cards have been purchased.");
        int player1Points = player1.totalAutomationPoints();
        int player2Points = player2.totalAutomationPoints();
        
        System.out.println("Player 1 total Automation Points: " + player1Points);
        System.out.println("Player 2 total Automation Points: " + player2Points);
        
        if (player1Points > player2Points) {
            System.out.println("\nPlayer 1 wins!");
        } else if (player2Points > player1Points) {
            System.out.println("\nPlayer 2 wins!");
        } else {
            System.out.println("\nIt's a tie!");
        }
    }
}
