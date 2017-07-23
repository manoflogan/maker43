// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Enum that encapsulates the different types of suits. Each suit represents a suit.
 */
public enum Suit {

    CLUB('c'),

    DIAMOND('d'),

    HEARTS('h'),

    SPADES('s');

    private final char suitType;

    /**
     * Enum constructor.
     *
     * @param suitType type of card value.
     */
    private Suit(char suitType) {
        this.suitType = suitType;
    }

    char getSuitType() {
        return this.suitType;
    }

    private static final Map<Character, Suit> SUITS = buildSuit();

    private static Map<Character, Suit> buildSuit() {
        Map<Character, Suit> suits = new LinkedHashMap<>();
        Suit[] values = Suit.values();
        for (Suit v : values) {
            suits.put(v.getSuitType(), v);
        }
        return Collections.unmodifiableMap(suits);
    }

    static Suit getSuit(Character c) {
        return SUITS.get(c);
    }

    @Override public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).append("[Suit = ").
            append(this.suitType).append("]").toString();
    }
}
