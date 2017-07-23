// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

/**
 * An instance of this class represents a player.
 *
 * <p>An instance of this class represents the player number and the hand.
 */
public class Player implements Comparable<Player> {

    private final int playerNumber;

    private final Hand hand;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.hand = new Hand();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Hand getHand() {
        return hand;
    }

    /**
     * Builds a hand for a player.
     *
     * <p>The line is a string that represents all the entire hand such as each space separated
     * string represents the distributed card. For example {@code 2c As 4d} represents the two of
     * clubs, ace of spaces and four of diamonds. In this example, the last character will always
     * represent the card suit, the remaining character represents the card weight.
     *
     * @param line line to be parsed
     */
    public void buildHand(String line) {
        if (line == null || line.isEmpty()) {
            return;
        }
        String[] cards = line.split(" ");
        for (String card : cards) {
            // Last character is the suit.
            card = card.trim();
            char suit = card.charAt(card.length() - 1);
            // Get the value.
            String cardWeight = card.substring(0, card.length() - 1).trim();
            this.hand.addCard(cardWeight, suit);
        }
        this.hand.calculateHandStatus();
    }

    @Override public int hashCode() {
        int hashCode = 31;
        hashCode = hashCode + this.playerNumber ^ 3;
        hashCode = this.hand != null ? this.hand.hashCode() ^ 5 : 3200 + hashCode;
        return hashCode;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Player.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Player p = (Player) obj;
        return this.playerNumber == p.getPlayerNumber() &&
            this.hand != null && this.hand.equals(p.getHand());
    }

    @Override public String toString() {
        return super.toString();
    }

    /**
     * The player is ranked on the basis of his hand.
     *
     * @param o player to be ranked
     * @return comparison value
     */
    @Override public int compareTo(Player o) {
        return this.hand.compareTo(o.getHand());
    }
}
