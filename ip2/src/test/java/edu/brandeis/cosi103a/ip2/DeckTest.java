package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
public class DeckTest {
    
    private Deck deck;
    
    @Before
    public void setUp() {
        deck = new Deck();
    }
    
    @Test
    public void testDeckInitialization() {
        assertEquals(8, deck.getNumberFrameworkCards());
    }
    
    @Test
    public void testBuyMethodCard() {
        Card card = deck.buyCard("Method", 2);
        assertNotNull(card);
        assertEquals("Method", card.getType());
        assertEquals(2, card.getCost());
        assertEquals(1, card.getValue());
    }
    
    @Test
    public void testBuyModuleCard() {
        Card card = deck.buyCard("Module", 5);
        assertNotNull(card);
        assertEquals("Module", card.getType());
        assertEquals(5, card.getCost());
        assertEquals(3, card.getValue());
    }
    
    @Test
    public void testBuyFrameworkCard() {
        Card card = deck.buyCard("Framework", 8);
        assertNotNull(card);
        assertEquals("Framework", card.getType());
        assertEquals(8, card.getCost());
        assertEquals(6, card.getValue());
        assertEquals(7, deck.getNumberFrameworkCards());
    }
    
    @Test
    public void testBuyBitcoinCard() {
        Card card = deck.buyCard("Bitcoin", 0);
        assertNotNull(card);
        assertEquals("Bitcoin", card.getType());
        assertEquals(0, card.getCost());
        assertEquals(1, card.getValue());
    }
    
    @Test
    public void testBuyEthereumCard() {
        Card card = deck.buyCard("Ethereum", 3);
        assertNotNull(card);
        assertEquals("Ethereum", card.getType());
        assertEquals(3, card.getCost());
        assertEquals(2, card.getValue());
    }
    
    @Test
    public void testBuyDogecoinCard() {
        Card card = deck.buyCard("Dogecoin", 6);
        assertNotNull(card);
        assertEquals("Dogecoin", card.getType());
        assertEquals(6, card.getCost());
        assertEquals(3, card.getValue());
    }
    
    @Test
    public void testBuyCardInsufficientCoins() {
        Card card = deck.buyCard("Framework", 7); // Need 8 coins
        assertNull(card);
        assertEquals(8, deck.getNumberFrameworkCards()); // Should not decrement
    }
    
    @Test
    public void testBuyAllFrameworks() {
        for (int i = 0; i < 8; i++) {
            Card card = deck.buyCard("Framework", 8);
            assertNotNull("Framework " + i + " should be purchasable", card);
        }
        assertEquals(0, deck.getNumberFrameworkCards());
        
        // 9th attempt should fail
        Card card = deck.buyCard("Framework", 8);
        assertNull(card);
    }
    
    @Test
    public void testGetBestAffordableCardWith2Coins() {
        String best = deck.getBestAffordableCard(2, 20);
        assertEquals("Method", best); // Only Method is affordable at 2 coins
    }
    
    @Test
    public void testGetBestAffordableCardWith5Coins() {
        String best = deck.getBestAffordableCard(5, 20);
        assertEquals("Module", best); // Module costs 5, best automation card
    }
    
    @Test
    public void testGetBestAffordableCardWith8Coins() {
        String best = deck.getBestAffordableCard(8, 20);
        assertEquals("Framework", best); // Framework costs 8, best automation card
    }
    
    @Test
    public void testGetBestAffordableCardWith0Coins() {
        String best = deck.getBestAffordableCard(0, 20);
        assertEquals("Bitcoin", best); // Bitcoin is free
    }
    
    @Test
    public void testGetBestAffordableCardWith4Coins() {
        String best = deck.getBestAffordableCard(4, 20);
        // Should get Method (2 coins) - automation cards are prioritized
        assertEquals("Method", best);
    }
    
    @Test
    public void testGetBestAffordableCardPrioritizesAutomation() {
        // With 5 coins, should prefer Module over Ethereum/Dogecoin
        String best = deck.getBestAffordableCard(5, 20);
        assertEquals("Module", best);
    }
    
    @Test
    public void testGetCardCost() {
        assertEquals(2, deck.getCardCost("Method"));
        assertEquals(5, deck.getCardCost("Module"));
        assertEquals(8, deck.getCardCost("Framework"));
        assertEquals(0, deck.getCardCost("Bitcoin"));
        assertEquals(3, deck.getCardCost("Ethereum"));
        assertEquals(6, deck.getCardCost("Dogecoin"));
    }
    
    @Test
    public void testGetCardCostInvalid() {
        assertEquals(-1, deck.getCardCost("InvalidCard"));
    }
    
    @Test
    public void testEdgeCaseNegativeCoins() {
        Card card = deck.buyCard("Bitcoin", -1);
        assertNull(card); // Should not be able to buy with negative coins
    }
    
    @Test
    public void testEdgeCaseAllMethodsExhausted() {
        // Buy all 14 Methods
        for (int i = 0; i < 14; i++) {
            Card card = deck.buyCard("Method", 2);
            assertNotNull("Method " + i + " should be purchasable", card);
        }
        
        // 15th attempt should fail
        Card card = deck.buyCard("Method", 2);
        assertNull(card);
        
        // But getBestAffordableCard should still work for other cards
        String best = deck.getBestAffordableCard(5, 20);
        assertEquals("Module", best);
    }
}
