// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

/**
 * An instance of this class represents a single game with multiple hands being distributed.
 */
public class Game {

    private final int numberOfPlayers;

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        assert this.numberOfPlayers > 24 :
            "Maximum number of players allowed is 24, not " + this.numberOfPlayers;
    }
}
