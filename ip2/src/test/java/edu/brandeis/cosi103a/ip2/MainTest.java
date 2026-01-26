package edu.brandeis.cosi103a.ip2;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Integration tests for the Main class and full game execution.
 */
public class MainTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testMainExecutesWithoutCrashing() {
        // Run the main method
        Main.main(new String[]{});
        
        // If we get here, the game completed without exceptions
        String output = outContent.toString();
        assertNotNull(output);
        assertTrue(output.length() > 0);
    }
    
    @Test
    public void testGameStartsWithCorrectMessage() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Game should announce start", 
                   output.contains("Game started!"));
        assertTrue("Should announce starting player", 
                   output.contains("Player 1") || output.contains("Player 2"));
        assertTrue("Should mention who goes first", 
                   output.contains("goes first"));
    }
    
    @Test
    public void testGameEndsWhenFrameworksExhausted() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Game should announce end", 
                   output.contains("Game Over!"));
        assertTrue("Should mention Framework cards", 
                   output.contains("Framework cards have been purchased"));
    }
    
    @Test
    public void testGameDisplaysRounds() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Game should display rounds", 
                   output.contains("ROUND 1"));
        assertTrue("Game should have multiple rounds", 
                   output.contains("ROUND 2") || output.contains("ROUND 3"));
    }
    
    @Test
    public void testGameDisplaysPlayerActions() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should show Player 1 actions", 
                   output.contains("-Player 1"));
        assertTrue("Should show Player 2 actions", 
                   output.contains("-Player 2"));
        assertTrue("Should show ACTION label", 
                   output.contains("ACTION:"));
        assertTrue("Should show bought cards", 
                   output.contains("Bought"));
    }
    
    @Test
    public void testGameDisplaysAutomationPoints() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display STATUS", 
                   output.contains("STATUS:"));
        assertTrue("Should show automation points", 
                   output.contains("Total AP is now"));
    }
    
    @Test
    public void testGameDisplaysFinalScores() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should show Player 1 final score", 
                   output.contains("Player 1 total Automation Points:"));
        assertTrue("Should show Player 2 final score", 
                   output.contains("Player 2 total Automation Points:"));
    }
    
    @Test
    public void testGameDeclaresWinner() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        boolean hasWinner = output.contains("Player 1 wins!") || 
                           output.contains("Player 2 wins!") || 
                           output.contains("It's a tie!");
        assertTrue("Game should declare a winner or tie", hasWinner);
    }
    
    @Test
    public void testGameHasReasonableLength() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        // Count rounds (each round should appear once)
        int roundCount = 0;
        for (int i = 1; i <= 100; i++) {
            if (output.contains("ROUND " + i)) {
                roundCount = i;
            } else {
                break;
            }
        }
        
        // Game should complete in reasonable number of rounds
        assertTrue("Game should have at least 10 rounds", roundCount >= 10);
        assertTrue("Game should complete within 100 rounds", roundCount <= 100);
    }
    
    @Test
    public void testBothPlayersGetTurns() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        
        // Count player appearances
        int player1Turns = countOccurrences(output, "-Player 1");
        int player2Turns = countOccurrences(output, "-Player 2");
        
        assertTrue("Player 1 should have multiple turns", player1Turns > 5);
        assertTrue("Player 2 should have multiple turns", player2Turns > 5);
        
        // Turns should be relatively balanced (within a few turns of each other)
        int difference = Math.abs(player1Turns - player2Turns);
        assertTrue("Players should have similar turn counts, difference was " + difference, 
                   difference <= 2);
    }
    
    @Test
    public void testAutomationPointsIncrease() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        
        // Both players should end with more than starting 3 points
        String[] lines = output.split("\n");
        int player1FinalPoints = 0;
        int player2FinalPoints = 0;
        
        for (String line : lines) {
            if (line.contains("Player 1 total Automation Points:")) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    player1FinalPoints = Integer.parseInt(parts[1].trim());
                }
            }
            if (line.contains("Player 2 total Automation Points:")) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    player2FinalPoints = Integer.parseInt(parts[1].trim());
                }
            }
        }
        
        assertTrue("Player 1 should gain points beyond starting 3, got " + player1FinalPoints, 
                   player1FinalPoints > 3);
        assertTrue("Player 2 should gain points beyond starting 3, got " + player2FinalPoints, 
                   player2FinalPoints > 3);
    }
    
    @Test
    public void testMultipleGameExecutions() {
        // Run game 3 times to ensure it's consistent
        for (int i = 0; i < 3; i++) {
            outContent.reset();
            Main.main(new String[]{});
            
            String output = outContent.toString();
            assertTrue("Game " + (i+1) + " should complete", 
                       output.contains("Game Over!"));
        }
    }
    
    @Test
    public void testGameOutputFormat() {
        Main.main(new String[]{});
        
        String output = outContent.toString();
        
        // Verify formatting patterns
        assertTrue("Should have proper ACTION format", 
                   output.contains("  ACTION:"));
        assertTrue("Should have proper STATUS format", 
                   output.contains("  STATUS:"));
        
        // Check for blank lines between rounds (proper spacing)
        assertTrue("Should have proper spacing", 
                   output.contains("\n\n"));
    }
    
    private int countOccurrences(String text, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }
}
