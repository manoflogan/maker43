// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@link Game}.
 */
public class GameTest {

    private Game game;

    @BeforeMethod
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.game = null;
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "playercards")
    public void testAddPlayer(String[] lines) throws Exception {
        for (String line: lines) {
            this.game.addPlayer(line);
        }

    }
}
