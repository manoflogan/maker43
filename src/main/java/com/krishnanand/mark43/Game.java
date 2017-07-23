// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An instance of this class represents a single game with multiple players participating.
 */
public class Game {

    private List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    /**
     * Adds a players and its the hand.
     *
     * <p>The player is represented as {@code 0 2c As 4d}. The input can be broken down as follows
     * <ul>
     *     <li>{@code 0}: Represents the player index. It will always be the first entry in
     *     the line, and will be followed by the hand to be dealt.</li>
     * </ul>
     *
     * <p>Invocation of this function also triggers the calculation of the rank.</p>
     *
     * @param line line to be parsed to create the player and its hand.
     */
    public void addPlayer(String line) {
        if (line == null || line.isEmpty()) {
            return;
        }
        int playerNumber = Integer.parseInt(String.valueOf(line.trim().charAt(0)), 10);
        Player player = new Player(playerNumber);
        player.buildHand(line.substring(1));
        this.players.add(player);
    }

    /**
     * Determines the winner. It will be the player with the largest hand weight.
     *
     * @return winning player
     */
    public Player determineTheWinner() {
        Collections.sort(this.players);
        return this.players.get(0);
    }


    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).
            append("Players = ").append(this.players).append("]").toString();
    }
}
