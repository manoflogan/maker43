// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * An instance of this class represents a single game with multiple players participating.
 */
public class Game {

    private List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
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
        this.players.add(player);
        player.buildHand(line.substring(1));
    }

    /**
     * Determines the winner. It will be the player with the largest hand weight.
     *
     * @return winning player
     */
    public List<Player> determineTheWinner() {
        Collections.sort(this.players);
        // Many players may have a straight flush with the same hands, but in different suits.
        Map<String, List<Player>> playerRanking = new LinkedHashMap<>();
        // KeySort by highest order.
        PriorityQueue<String> keyQueue = new PriorityQueue<>((o1,  o2) -> {
            return -1 * (o1.compareTo(o2));

        });
        for (Player player : this.players) {
            Hand hand = player.getHand();
            // The key is a combination of the hand weight, and highest card weight
            String key =
                String.format("%d-%d", hand.getHandWeight(),
                    hand.getPlayingCards().get(0).getCardWeight().getPriority());
            if (playerRanking.containsKey(key)) {
                playerRanking.get(key).add(player);
            } else {
                List<Player> playerList = new ArrayList<>();
                playerList.add(player);
                playerRanking.put(key, playerList);
            }
            if (!keyQueue.contains(key)) {
                keyQueue.add(key);
            }

        }
        return playerRanking.get(keyQueue.poll());
    }


    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).
            append("Players = ").append(this.players).append("]").toString();
    }
}
