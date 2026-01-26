package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the CryptocurrencyCard class.
 */
public class CryptocurrencyCardTest {
    
    @Test
    public void testCryptocurrencyCardCreation() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        assertEquals(60, card.getCount("Bitcoin"));
        assertEquals(40, card.getCount("Ethereum"));
        assertEquals(30, card.getCount("Dogecoin"));
    }
    
    @Test
    public void testDecrementCount() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        
        card.decrementCount("Bitcoin");
        assertEquals(59, card.getCount("Bitcoin"));
        
        card.decrementCount("Ethereum");
        assertEquals(39, card.getCount("Ethereum"));
        
        card.decrementCount("Dogecoin");
        assertEquals(29, card.getCount("Dogecoin"));
    }
    
    @Test
    public void testDecrementToZero() {
        CryptocurrencyCard card = new CryptocurrencyCard(1, 1, 1);
        
        card.decrementCount("Bitcoin");
        assertEquals(0, card.getCount("Bitcoin"));
        
        // Should not go below zero
        card.decrementCount("Bitcoin");
        assertEquals(0, card.getCount("Bitcoin"));
    }
    
    @Test
    public void testGetCost() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        
        assertEquals(0, card.getCost("Bitcoin"));
        assertEquals(3, card.getCost("Ethereum"));
        assertEquals(6, card.getCost("Dogecoin"));
    }
    
    @Test
    public void testGetValue() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        
        assertEquals(1, card.getValue("Bitcoin"));
        assertEquals(2, card.getValue("Ethereum"));
        assertEquals(3, card.getValue("Dogecoin"));
    }
    
    @Test
    public void testInvalidCardName() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        
        assertEquals(0, card.getCount("InvalidCrypto"));
        assertEquals(-1, card.getCost("InvalidCrypto"));
        assertEquals(-1, card.getValue("InvalidCrypto"));
    }
    
    @Test
    public void testMassDecrements() {
        CryptocurrencyCard card = new CryptocurrencyCard(60, 40, 30);
        
        // Decrement Bitcoin 60 times (should stop at 0)
        for (int i = 0; i < 70; i++) {
            card.decrementCount("Bitcoin");
        }
        assertEquals(0, card.getCount("Bitcoin"));
    }
    
    @Test
    public void testEdgeCaseZeroInitialCount() {
        CryptocurrencyCard card = new CryptocurrencyCard(0, 0, 0);
        
        assertEquals(0, card.getCount("Bitcoin"));
        
        card.decrementCount("Bitcoin");
        assertEquals(0, card.getCount("Bitcoin")); // Should stay at 0
    }
}
