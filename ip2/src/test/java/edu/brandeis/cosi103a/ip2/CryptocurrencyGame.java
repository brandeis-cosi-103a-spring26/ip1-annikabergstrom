package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration tests for the full cryptocurrency card game.
 */
public class CryptocurrencyGame {
    
    @Test
    public void testFullGameCompletesSuccessfully() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Deck deck = new Deck();
        
        int initialFrameworks = deck.getNumberFrameworkCards();
        assertEquals(8, initialFrameworks);
        
        // Simulate game until all frameworks are gone
        int turnCount = 0;
        while (deck.getNumberFrameworkCards() > 0 && turnCount < 100) {
            turnCount++;
            player1.buyPhase(deck, turnCount);
            player1.cleanUp();
            if (deck.getNumberFrameworkCards() <= 0) break;
            
            player2.buyPhase(deck, turnCount);
            player2.cleanUp();
        }
        
        // Game should have ended
        assertEquals(0, deck.getNumberFrameworkCards());
        assertTrue(turnCount < 100); // Should complete in reasonable time
        
        // Both players should have automation points
        int p1Points = player1.totalAutomationPoints();
        int p2Points = player2.totalAutomationPoints();
        assertTrue(p1Points > 0);
        assertTrue(p2Points > 0);
    }
    
    @Test
    public void testPlayersStartWithCorrectDeck() {
        Player player = new Player(1);
        
        // Player starts with 3 automation points (3 Method cards worth 1 each)
        assertEquals(3, player.totalAutomationPoints());
        
        // Player should be able to play cryptocurrencies
        int coins = player.playCryptocurrencies();
        assertTrue(coins >= 0 && coins <= 7); // 0-7 bitcoins in hand of 5
    }
    
    @Test
    public void testMultiplePlayersIndependent() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Deck deck = new Deck();
        
        String card1 = player1.buyPhase(deck, 1);
        String card2 = player2.buyPhase(deck, 1);
        
        // Both should be able to buy
        assertNotNull(card1);
        assertNotNull(card2);
        
        // Their points can be different
        int p1Points = player1.totalAutomationPoints();
        int p2Points = player2.totalAutomationPoints();
        assertTrue(p1Points >= 3); // At least starting 3
        assertTrue(p2Points >= 3);
    }
    
    @Test
    public void testDeckDepletion() {
        Deck deck = new Deck();
        
        // Buy all Framework cards
        for (int i = 0; i < 8; i++) {
            Card card = deck.buyCard("Framework", 8);
            assertNotNull("Should be able to buy Framework " + i, card);
        }
        
        // 9th purchase should fail
        Card card = deck.buyCard("Framework", 8);
        assertNull("Should not be able to buy 9th Framework", card);
        assertEquals(0, deck.getNumberFrameworkCards());
    }
}
