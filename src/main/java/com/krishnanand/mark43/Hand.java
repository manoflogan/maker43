// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * An instance of the card represents a set of cards representing a hand.
 */
public class Hand implements Comparable<Hand> {

    private List<Card> playingCards = new LinkedList<>();

    // Returns {@code true} if the hand is a straight flush. Final Value = (isStraight & isFlush)
    private boolean isStraightFlush;

    // Returns {@code true} if the hand is a three of a kind.
    private boolean isThreeOfAKind;

    // Returns {@code true} if it is a straight hand.
    private boolean isStraight;

    // Returns {@code true} if it is a flush.
    private boolean isFlush;

    // Returns {@code true} if there are two cards of the same suit type.
    private boolean isPair;

    // Returns {@code true} if the card does not fall in any category.
    private boolean isHighCard;

    // Exposed for testing purposes only.
    boolean isStraightFlush() {
        return isStraightFlush;
    }

    boolean isThreeOfAKind() {
        return isThreeOfAKind;
    }

    boolean isStraight() {
        return isStraight;
    }

    boolean isFlush() {
        return isFlush;
    }

    boolean isPair() {
        return isPair;
    }

    boolean isHighCard() {
        return isHighCard;
    }
    // End exposed for testing purposes.

    // Determines the handWeight of the individual. Person with the highest handWeight wins.
    private long handWeight = 0;

    public long getHandWeight() {
        return handWeight;
    }

    // Package private only.
    List<Card> getPlayingCards() {
        return playingCards;
    }

    /**
     * Adds cards to the hand.
     *
     * @param cardWeight card weight
     * @param suitType suit type
     */
    public void addCard(String cardWeight, char suitType) {
        this.playingCards.add(Card.buildCard(cardWeight, suitType));
    }

    /**
     * Calculates the current hand status and assigns an arbitary score.
     */
    public void calculateHandStatus() {
        // First sort by hand value.
        Collections.sort(this.playingCards);
        this.checkHandStatus();
    }

    /**
     * Determines the current hand status.
     */
    private void checkHandStatus() {
        // Assume that at least
        Card firstCard = this.playingCards.get(0);

        // Initialise the flags
        this.isThreeOfAKind = true;

        this.isStraight = true;

        this.isFlush = true;

        int counter = 1;

        // Used to calculate the pairs.
        int numberOfCardsWithSameRank = 1;
        while(counter < this.playingCards.size()) {
            Card otherCard = this.playingCards.get(counter);

            // Check if three of a kind.
            this.isThreeOfAKind = this.isThreeOfAKind & (
                firstCard.getCardWeight().getRank().equals(otherCard.getCardWeight().getRank()));


            // Check if the value is straight.
            this.isStraight = this.isStraight &
                (Math.abs(otherCard.getCardWeight().getPriority() -
                    firstCard.getCardWeight().getPriority()) == 1);

            // Check if the hand is a flush.
            this.isFlush = this.isFlush & (otherCard.getSuit() == firstCard.getSuit());

            // Check if the hand is a straight flush.
            this.isStraightFlush = this.isStraight & this.isFlush;

            // Checking for pairs. At this stage, we have determined if that there are two cards
            // with the same rank.
            if (this.isThreeOfAKind) {
                numberOfCardsWithSameRank ++;
            } else {
                numberOfCardsWithSameRank =
                    numberOfCardsWithSameRank > 1 ? numberOfCardsWithSameRank : 1; // Reset
                // Checking for the use case when the first does not match, but the second and
                // the third do.
                if (firstCard.getCardWeight().getRank().equals(
                        otherCard.getCardWeight().getRank())) {
                    numberOfCardsWithSameRank ++;
                }
            }
            counter ++;
            firstCard = otherCard;
        }

        // Only two cards can have the same rank.
        this.isPair = numberOfCardsWithSameRank == 2;

        // Fallback.
        this.isHighCard = !this.isStraightFlush && !this.isThreeOfAKind && !this.isFlush &&
            !this.isStraight && !this.isPair;


        // We assign the weight for the current hand situation by assigning an arbitrary
        // differentiating number. This will be used in case there are other card will the same
        if (this.isStraightFlush) {
            this.handWeight = 100;
        } else if (this.isThreeOfAKind) {
            this.handWeight = 90;
        } else if (this.isStraight) {
            this.handWeight = 80;
        } else if (this.isFlush) {
            this.handWeight = 70;
        } else if (this.isPair) {
            this.handWeight = 60;
        } else if (this.isHighCard) {
            this.handWeight = 50;
        }
    }

    /**
     * The implementation first sorts by hand weight and then by the highest card.
     *
     * @param o object to be compared
     * @return comparison value
     */
    @Override public int compareTo(Hand o) {
        long diff = this.handWeight - o.getHandWeight();
        if (diff != 0) {
           return (int) (-1 * diff);
        }
        // Get the first element in the list. Since the list is sorted by descending order, the
        // hand with the card as the highest first element wins.
        return -1 * (
            this.playingCards.get(0).getCardWeight().getPriority() -
                o.getPlayingCards().get(0).getCardWeight().getPriority());
    }

    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).append("[Hand Weight = ")
            .append(this.handWeight).append(", Cards = ").append(this.getPlayingCards())
            .append("]").toString();
    }

    @Override public int hashCode() {
        int hashCode = 31;
        hashCode = ((int) this.handWeight ^ 5) + hashCode;
        return hashCode;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Hand.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Hand h = (Hand) obj;
        return this.handWeight == h.getHandWeight() &&
            (this.playingCards.get(0).getCardWeight().getPriority() ==
                h.getPlayingCards().get(0).getCardWeight().getPriority());
    }
}
