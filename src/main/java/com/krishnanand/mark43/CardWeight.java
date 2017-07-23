// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Enum encasulating the card weight.
 */
public enum CardWeight {
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4" , 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7),
    NINE("9", 8),
    TEN("10", 9),
    JACK("J", 10),
    QUEEN("Q", 11),
    KING("K", 12),
    ACE("A", 13);

    private final String rank;

    private final int priority;

    public static final Map<String, CardWeight> CARDS = buildPriorityMap();

    /**
     * Builds the ordinality of the map.
     * @return
     */
    private static Map<String, CardWeight> buildPriorityMap() {
        Map<String, CardWeight> map = new LinkedHashMap<>();
        for (CardWeight cw : values) {
            map.put(cw.getRank(), cw);
        }
        return Collections.unmodifiableMap(map);
    }

    private CardWeight(String value, int priority) {
        this.rank = value;
        this.priority = priority;
    }

    @Override public String toString() {
        return new StringBuilder().append(this.getClass().getSimpleName()).
            append("[Card Rank = ").append(this.rank).append(", Priority = ").
            append(this.priority).append("]").toString();
    }

    String getRank() {
        return this.rank;
    }

    public int getPriority() {
        return priority;
    }

    private static CardWeight[] values = values();




}
