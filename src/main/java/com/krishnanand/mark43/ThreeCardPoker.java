// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * An entry class representing the simulation of the three card poker.
 */
public class ThreeCardPoker {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            ThreeCardPoker poker = new ThreeCardPoker();
            List<Player> winningPlayers = poker.playGame(scanner);
            poker.displayResults(winningPlayers);
        }
    }

    /**
     * Calls the game. T
     *
     * <p>The input is always in the following format as given below:
     *  <ul>
     *     <li>The first line is always the number of players participating.</li>
     *     <li>The next "N" lines represent the player number, and their hands.</li>
     * </ul>
     *
     * <p>The sample input value is given below:
     * {@code 3
         0 2c As 4d
         1 Kd 5h 6c
         2 Jc Jd 9s}.
     * </p>
     *
     * <p>In this example, input on the first line {@code 3} represents the number of players
     * that will be playing the game. This is followed by {@code 0 2c As 4d}, in which the first
     * {@code 0} represents the player number or index, the next substring {@code 2c As 4d}
     * represents the player's hand. In this example, the player has the two of clubs, Ace of
     * spades, and four of diamonds. Similarly player 1 and player 2 have the king of diamonds, five
     * of hearts, and 6 of clubs, and Jack of clubs, Jack of diamonds, and nine of spaces
     * respectively.</p>
     *
     * @param scanner scanner to read values
     */
    List<Player> playGame(Scanner scanner) {
        boolean isValidInputFound = false;
        // The input files have certain on top which need to be ignored.
        // Continue until the value input is reached.
        int numberOfPlayers = 0;
        while (!isValidInputFound) {
            try {
                String n = scanner.nextLine();
                numberOfPlayers = Integer.parseInt(n);
                isValidInputFound = true;
                break;
            } catch (NumberFormatException ime) {
                // Invalid input. Ignore.
                continue;
            }
        }

        // Extract the number of players

        if (numberOfPlayers == 0) {
            throw new IllegalArgumentException("No players have been selected");
        }
        assert numberOfPlayers < 24 :
            new StringBuilder("The number of players will always be less than 24. ").append(
                "You have selected").append(numberOfPlayers).toString();

        Game game = new Game();
        int counter = 0;
        while (counter < numberOfPlayers) {
            String line = scanner.nextLine();
            game.addPlayer(line);
            counter ++;
        }
        List<Player> players =  game.determineTheWinner();
        return players;
    }

    void displayResults(List<Player> players) {
        int s = players.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s; i++) {
            sb.append(players.get(i).getPlayerNumber());
            if (i < (s - 1)) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
