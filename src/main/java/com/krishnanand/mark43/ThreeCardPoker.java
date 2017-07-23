// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.Scanner;

/**
 * An entry class representing the simulation of the three card poker.
 */
public class ThreeCardPoker {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            ThreeCardPoker poker = new ThreeCardPoker();
            poker.playGame(scanner);
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
    void playGame(Scanner scanner) {

        // Extract the number of players
        int numberOfPlayers = scanner.nextInt();
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
        Player player = game.determineTheWinner();
        System.out.println(player.getPlayerNumber());
    }
}
