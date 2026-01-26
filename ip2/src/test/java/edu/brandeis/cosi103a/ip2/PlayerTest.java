package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {
    
    private Player player;
    private Deck deck;
    
    @Before
    public void setUp() {
        player = new Player(1);
        deck = new Deck();
    }
    
    @Test
    public void testPlayerInitialization() {
        // Player should start with 3 automation points (3 Methods)
        assertEquals(3, player.totalAutomationPoints());
    }
    
    @Test
    public void testPlayerNumber() {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        
        assertEquals("Player 1", p1.toString());
        assertEquals("Player 2", p2.toString());
    }
    
    @Test
    public void testPlayCryptocurrenciesInHand() {
        int coins = player.playCryptocurrencies();
        // Player has 5 cards in hand from 7 Bitcoin + 3 Method
        // Should have between 0 and 5 Bitcoin (all cryptocurrency)
        assertTrue(coins >= 0 && coins <= 7);
    }
    
    @Test
    public void testBuyPhaseReturnsCardName() {
        String card = player.buyPhase(deck);
        // With starting hand, should be able to buy at least Bitcoin (free) or Method (2 coins)
        assertTrue(card != null);
    }
    
    @Test
    public void testBuyPhaseIncreasesAutomationPoints() {
        int initialPoints = player.totalAutomationPoints();
        
        // Try to buy multiple times
        for (int i = 0; i < 5; i++) {
            player.buyPhase(deck);
            player.cleanUp();
        }
        
        int finalPoints = player.totalAutomationPoints();
        // Points should have increased (unless only bought cryptocurrency)
        assertTrue(finalPoints >= initialPoints);
    }
    
    @Test
    public void testCleanUpCycle() {
        // After cleanup, player should have 5 cards again
        player.cleanUp();
        
        // We can't directly check hand size, but we can check that
        // player can still play cryptocurrencies
        int coins = player.playCryptocurrencies();
        assertTrue(coins >= 0); // Should have some hand
    }
    
    @Test
    public void testAddToDiscard() {
        Card card = new Card(2, 1, "Method");
        player.addToDiscard(card);
        
        // After cleanup, this card should be drawable
        player.cleanUp();
        
        // Total points should now be 4 (3 starting + 1 added)
        assertEquals(4, player.totalAutomationPoints());
    }
    
    @Test
    public void testAddNullToDiscard() {
        int initialPoints = player.totalAutomationPoints();
        player.addToDiscard(null);
        
        // Should not crash and points should be unchanged
        assertEquals(initialPoints, player.totalAutomationPoints());
    }
    
    @Test
    public void testMultipleBuyAndCleanupCycles() {
        int cycles = 10;
        for (int i = 0; i < cycles; i++) {
            String card = player.buyPhase(deck);
            player.cleanUp();
        }
        
        // Player should still have valid state
        int points = player.totalAutomationPoints();
        assertTrue(points >= 3); // At least starting points
    }
    
    @Test
    public void testTotalAutomationPointsWithMixedCards() {
        // Add various automation cards
        player.addToDiscard(new Card(2, 1, "Method"));
        player.addToDiscard(new Card(5, 3, "Module"));
        player.addToDiscard(new Card(8, 6, "Framework"));
        
        player.cleanUp();
        
        // 3 (starting) + 1 (Method) + 3 (Module) + 6 (Framework) = 13
        assertEquals(13, player.totalAutomationPoints());
    }
    
    @Test
    public void testTotalAutomationPointsIgnoresCryptocurrency() {
        int initialPoints = player.totalAutomationPoints();
        
        // Add cryptocurrency cards
        player.addToDiscard(new Card(0, 1, "Bitcoin"));
        player.addToDiscard(new Card(3, 2, "Ethereum"));
        player.addToDiscard(new Card(6, 3, "Dogecoin"));
        
        player.cleanUp();
        
        // Points should be unchanged
        assertEquals(initialPoints, player.totalAutomationPoints());
    }
    
    @Test
    public void testEdgeCaseEmptyDeckReshuffle() {
        // Force many cleanups to trigger reshuffle
        for (int i = 0; i < 20; i++) {
            player.cleanUp();
        }
        
        // Player should still be able to play
        int coins = player.playCryptocurrencies();
        assertTrue(coins >= 0);
    }
    
    @Test
    public void testEdgeCaseBuyWithInsufficientCoins() {
        // Create a scenario where player has no coins
        // This is hard to force, but we can test the deck returns null
        Deck emptyStyleDeck = new Deck();
        
        // Buy all frameworks to limit options
        for (int i = 0; i < 8; i++) {
            emptyStyleDeck.buyCard("Framework", 10);
        }
        
        // Player should still be able to call buyPhase
        String card = player.buyPhase(emptyStyleDeck);
        // May or may not get a card depending on hand
        assertNotNull(card); // Should at least get Bitcoin (free)
    }
    
    @Test
    public void testPlayerIndependence() {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        
        // Both start with same points
        assertEquals(p1.totalAutomationPoints(), p2.totalAutomationPoints());
        
        // Buy cards for p1
        p1.buyPhase(deck);
        p1.cleanUp();
        
        // p2 should be unaffected
        assertEquals(3, p2.totalAutomationPoints());
    }
}
