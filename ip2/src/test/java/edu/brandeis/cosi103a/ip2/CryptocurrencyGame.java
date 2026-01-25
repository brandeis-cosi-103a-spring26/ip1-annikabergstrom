package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import static org.junit.Assert.*;

public class CryptocurrencyGame {
    @Test
    public void testCryptocurrencyCardCreation() {
        CryptocurrencyCard card = new CryptocurrencyCard(10, 20, 4);
        assertEquals(10, card.getCount("Bitcoin"));
        assertEquals(20, card.getCount("Ethereum"));
        assertEquals(4, card.getCount("Dogecoin"));
    }
}
