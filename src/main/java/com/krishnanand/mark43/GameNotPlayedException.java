// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

/**
 * Thrown when the game can not be played for any reason.
 */
public class GameNotPlayedException extends RuntimeException {

    GameNotPlayedException() {
        super("The game could not be played as the test files could not be loaded.");
    }
}
