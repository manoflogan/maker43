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
        Map<OrderOfPlayers, List<Player>> playerRanking = new LinkedHashMap<>();
        // KeySort by highest order. I could use a priority queue, but it is not giving the correct
        // answer for all high pair values.
        PriorityQueue<OrderOfPlayers> keyQueue = new PriorityQueue<>(new Comparator<OrderOfPlayers>() {
            @Override public int compare(OrderOfPlayers o1, OrderOfPlayers o2) {
                int handWeight = -1 * ((int) (o1.getHandWeight() - o2.getHandWeight()));
                if (handWeight != 0) {
                    return handWeight;
                }
                return -1 * (o1.getPriority() - o2.getPriority());
            }
        });
        for (Player player : this.players) {
            Hand hand = player.getHand();
            // The key is a combination of the hand weight, and highest card weight
            OrderOfPlayers order =
                new OrderOfPlayers(
                    hand.getHandWeight(),
                    hand.getPlayingCards().get(0).getCardWeight().getPriority());

            if (playerRanking.containsKey(order)) {
                playerRanking.get(order).add(player);
            } else {
                List<Player> playerList = new ArrayList<>();
                playerList.add(player);
                playerRanking.put(order, playerList);
            }
            if (!keyQueue.contains(order)) {
                keyQueue.add(order);
            }

        }

        return playerRanking.get(keyQueue.poll());
    }


    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).
            append("[Players = ").append(this.players).append("]").toString();
    }

    /**
     * An instance of this class encapsulates the ranking and priority of the card to be used
     * to determine the winners.
     */
    static class OrderOfPlayers {

        private final long handWeight;

        private final int priority;

        OrderOfPlayers(long handWeight, int priority) {
            this.handWeight = handWeight;
            this.priority = priority;
        }

        public long getHandWeight() {
            return handWeight;
        }

        public int getPriority() {
            return priority;
        }

        @Override public String toString() {
            return new StringBuilder(this.getClass().getSimpleName()).append("[Weight = ").
                append(this.handWeight).append(", Priority = ").append(this.priority).append("]").
                toString();
        }

        @Override public int hashCode() {
            int hashCode = 31;
            hashCode = (int) this.handWeight ^ 5 + hashCode;
            hashCode = hashCode + this.priority ^ 3;
            return hashCode;
        }

        @Override public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!OrderOfPlayers.class.isAssignableFrom(obj.getClass())) {
                return false;
            }
            OrderOfPlayers ow = (OrderOfPlayers) obj;
            return this.handWeight == ow.getHandWeight() && this.priority == ow.getPriority();
        }
    }
}
