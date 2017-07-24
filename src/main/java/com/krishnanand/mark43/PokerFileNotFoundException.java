// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

/**
 * Thrown when no poker file was either not read or found for any reason.
 */
public class PokerFileNotFoundException extends RuntimeException {

    /**
     * Constructor for {@link PokerFileNotFoundException}.
     *
     * @param filename file name that could be not be read
     * @param t root underlying exception
     */
    public PokerFileNotFoundException(String filename, Throwable t) {
        super(String.format("The poker file %s was not found.", filename), t);
    }
}
