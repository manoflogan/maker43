// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

/**
 * An instance of the class represents one set of cards.
 */
public class Card implements Comparable<Card> {

    private final CardWeight cardWeight;

    private final Suit suit;

    public CardWeight getCardWeight() {
        return cardWeight;
    }

    public Suit getSuit() {
        return suit;
    }

    private Card(String cardWeight, char suitType) {
        this.cardWeight = CardWeight.getCardWeight(cardWeight);
        this.suit = Suit.getSuit(suitType);
    }


    @Override public int hashCode() {
        int hashCode = 31;
        hashCode += this.cardWeight != null ? this.cardWeight.hashCode() ^ 4 : 478320;
        hashCode += this.suit != null ? this.suit.hashCode() ^ 5 : 37829;
        return hashCode;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Card.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Card other = (Card) obj;
        return (this.cardWeight == other.getCardWeight()  && this.suit == other.getSuit());
    }

    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).append("Card Weight = ").
            append(this.cardWeight).append(", Suit = ").append(this.suit).append("]").toString();
    }

    /**
     * Sort in descending order and then by suit.
     */
    @Override public int compareTo(Card o) {
        int diff = (o.getCardWeight().getPriority() - this.getCardWeight().getPriority());
        if (diff == 0) {
            // Sort by suit.
            return o.suit.getSuitType() - this.suit.getSuitType();
        }
        return diff;

    }

    /**
     * Builds a card of card weight.
     *
     * @param cardWeight card value; the value can be anyone from "2" to "A"
     * @param suitType suit type; the value can be either 'c', 'd', 'h', or 's'.
     * @return card instance
     */
    public static Card buildCard(String cardWeight, char suitType) {
        return new Card(cardWeight, suitType);
    }
}
