package edu.brandeis.cosi103a.ip1;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String[] players = {"Player 1", "Player 2"};
        int[] scores = {0, 0};
        final int TURNS = 10;

        System.out.println("Two-player Dice Game");
        System.out.println("Each player has " + TURNS + " turns. On your turn you roll one 6-sided die.");
        System.out.println("You may reroll up to 2 times. When you end your turn, the die value is added to your score.");
        System.out.println("Enter 'r' to reroll, or 'e' to end and keep the current value.");
        System.out.println();

        for (int turn = 1; turn <= TURNS; turn++) {
            for (int p = 0; p < players.length; p++) {
                System.out.println("--- " + players[p] + " - Turn " + turn + " ---");
                int rerollsLeft = 2;
                int value = rollDie(rand);
                System.out.println("Rolled: " + value + ". Rerolls left: " + rerollsLeft);

                while (rerollsLeft > 0) {
                    System.out.print("(r = reroll, e = end) > ");
                    String line = in.nextLine().trim().toLowerCase();
                    if (line.isEmpty()) {
                        continue;
                    }
                    char cmd = line.charAt(0);
                    if (cmd == 'r') {
                        value = rollDie(rand);
                        rerollsLeft--;
                        System.out.println("Rerolled: " + value + ". Rerolls left: " + rerollsLeft);
                        if (rerollsLeft == 0) {
                            System.out.println("No rerolls left. End turn to add " + value + " to your score.");
                        }
                    } else if (cmd == 'e') {
                        break;
                    } else {
                        System.out.println("Unknown command. Enter 'r' or 'e'.");
                    }
                }

                scores[p] += value;
                System.out.println(players[p] + " gains " + value + " points. Total: " + scores[p]);
                System.out.println();
            }
        }

        System.out.println("=== Final Scores ===");
        System.out.println(players[0] + ": " + scores[0]);
        System.out.println(players[1] + ": " + scores[1]);

        if (scores[0] > scores[1]) {
            System.out.println(players[0] + " wins!");
        } else if (scores[1] > scores[0]) {
            System.out.println(players[1] + " wins!");
        } else {
            System.out.println("It's a tie!");
        }

        in.close();
    }

    private static int rollDie(Random rand) {
        return rand.nextInt(6) + 1;
    }
}
