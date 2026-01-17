package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class AppTest {

    @Test
    public void testRollDieRange() {
        Random rand = new Random(0);
        for (int i = 0; i < 1000; i++) {
            int v = App.rollDie(rand);
            assertTrue("roll out of range: " + v, v >= 1 && v <= 6);
        }
    }

    @Test
    public void testDetermineWinner() {
        assertEquals(0, App.determineWinner(new int[] { 10, 5 }));
        assertEquals(1, App.determineWinner(new int[] { 3, 7 }));
        assertEquals(-1, App.determineWinner(new int[] { 4, 4 }));
    }

    @Test
    public void testPlayGame_NoRerolls() {
        // provide 'e' (end) for every player turn so only initial rolls count
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("e\n");
        }

        Scanner in = new Scanner(new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8)));
        Random rand = new Random(12345);

        int[] scores = App.playGame(rand, in, 10);
        assertEquals(2, scores.length);

        int min = 10; // minimum possible (10 turns * 1 point)
        int max = 60; // maximum possible (10 turns * 6 points)
        assertTrue(scores[0] >= min && scores[0] <= max);
        assertTrue(scores[1] >= min && scores[1] <= max);
    }
}
