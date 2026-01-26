package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the AutomationCard class.
 */
public class AutomationCardTest {
    
    @Test
    public void testAutomationCardCreation() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        assertEquals(14, card.getCount("Method"));
        assertEquals(8, card.getCount("Module"));
        assertEquals(8, card.getCount("Framework"));
    }
    
    @Test
    public void testGetNumberFrameworkCards() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        assertEquals(8, card.getNumberFrameworkCards());
    }
    
    @Test
    public void testDecrementCount() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        
        card.decrementCount("Method");
        assertEquals(13, card.getCount("Method"));
        
        card.decrementCount("Module");
        assertEquals(7, card.getCount("Module"));
        
        card.decrementCount("Framework");
        assertEquals(7, card.getCount("Framework"));
    }
    
    @Test
    public void testDecrementToZero() {
        AutomationCard card = new AutomationCard(1, 1, 1);
        
        card.decrementCount("Method");
        assertEquals(0, card.getCount("Method"));
        
        // Should not go below zero
        card.decrementCount("Method");
        assertEquals(0, card.getCount("Method"));
    }
    
    @Test
    public void testGetCost() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        
        assertEquals(2, card.getCost("Method"));
        assertEquals(5, card.getCost("Module"));
        assertEquals(8, card.getCost("Framework"));
    }
    
    @Test
    public void testGetValue() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        
        assertEquals(1, card.getValue("Method"));
        assertEquals(3, card.getValue("Module"));
        assertEquals(6, card.getValue("Framework"));
    }
    
    @Test
    public void testInvalidCardName() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        
        assertEquals(0, card.getCount("InvalidCard"));
        assertEquals(-1, card.getCost("InvalidCard"));
        assertEquals(-1, card.getValue("InvalidCard"));
    }
    
    @Test
    public void testMultipleDecrements() {
        AutomationCard card = new AutomationCard(14, 8, 8);
        
        for (int i = 0; i < 5; i++) {
            card.decrementCount("Method");
        }
        assertEquals(9, card.getCount("Method"));
    }
    
    @Test
    public void testEdgeCaseZeroInitialCount() {
        AutomationCard card = new AutomationCard(0, 0, 0);
        
        assertEquals(0, card.getCount("Method"));
        assertEquals(0, card.getNumberFrameworkCards());
        
        card.decrementCount("Method");
        assertEquals(0, card.getCount("Method")); // Should stay at 0
    }
}
