package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Card class.
 */
public class CardTest {
    
    @Test
    public void testCardCreation() {
        Card card = new Card(5, 3, "Module");
        assertEquals(5, card.getCost());
        assertEquals(3, card.getValue());
        assertEquals("Module", card.getType());
    }
    
    @Test
    public void testCardWithZeroCost() {
        Card card = new Card(0, 1, "Bitcoin");
        assertEquals(0, card.getCost());
        assertEquals(1, card.getValue());
        assertEquals("Bitcoin", card.getType());
    }
    
    @Test
    public void testCardWithHighValues() {
        Card card = new Card(8, 6, "Framework");
        assertEquals(8, card.getCost());
        assertEquals(6, card.getValue());
        assertEquals("Framework", card.getType());
    }
    
    @Test
    public void testDifferentCardTypes() {
        Card automation = new Card(2, 1, "Method");
        Card crypto = new Card(3, 2, "Ethereum");
        
        assertNotEquals(automation.getType(), crypto.getType());
        assertNotEquals(automation.getCost(), crypto.getCost());
    }
}
