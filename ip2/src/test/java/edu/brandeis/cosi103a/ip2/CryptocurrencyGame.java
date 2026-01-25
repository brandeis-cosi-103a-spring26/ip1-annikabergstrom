package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

public class CryptocurrencyGame {
    @Test
    public void testCryptocurrencyCardCreation() {
        CryptocurrencyCard card = new CryptocurrencyCard(10, 20, "Bitcoin");
        assertEquals(10, card.getCost());
        assertEquals(20, card.getValue());
        assertEquals("Bitcoin", card.getType());
    }
}
