package com.krishnanand.mark43;

/**
 * Thrown when the game can not be played for any reason.
 */
public class GameNotPlayedException extends RuntimeException {

    /**
     * Root constructor.
     */
    public GameNotPlayedException() {
        super("The game could not be played as the test files could not be loaded.");
    }
}
