package edu.brandeis.cosi103a.ip2;
import java.util.Random;

/**
 * Main class for running the cryptocurrency card game. Manages the game loop, alternating turns 
 * between two automated players, determining the winner based on total Automation Points.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
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
                String card1 = player1.buyPhase(decks);
                player1.cleanUp();
                printLog(player1, card1);

                if (decks.getNumberFrameworkCards() <= 0) {
                    break;
                }
                
                // Player 2's turn
                String card2 = player2.buyPhase(decks);
                player2.cleanUp();
                printLog(player2, card2);

            } else {
                // Player 2's turn
                String card2 = player2.buyPhase(decks);
                player2.cleanUp();
                printLog(player2, card2);
                
                if (decks.getNumberFrameworkCards() <= 0) {
                    break;
                }
                
                // Player 1's turn
                String card1 = player1.buyPhase(decks);
                player1.cleanUp();
                printLog(player1, card1);
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

    /**
     * Prints the log for a player's turn including action taken and current status. In a seperate function for clarity.
     * @param player the player whose turn it is
     * @param card card bought this turn, null if none
     */
    public static void printLog(Player player, String card) {
        System.out.println("-Player " + player.getPlayerNumber());
        System.out.println("  ACTION: Bought " + (card != null ? card : "nothing"));
        System.out.println("  STATUS: Total AP is now " + player.totalAutomationPoints());
    }
}
