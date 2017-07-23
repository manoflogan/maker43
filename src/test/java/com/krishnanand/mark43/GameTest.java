// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.List;
import org.testng.Assert;
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

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "2winnercards")
    public void testAddPlayer(String[] lines) throws Exception {
        for (String line: lines) {
            this.game.addPlayer(line);
        }
        Assert.assertEquals(this.game.getPlayers().size(), 4);
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "2winnercards")
    public void testDetermineWinner_2Winners(String[] lines) throws Exception {
        for (String line: lines) {
            this.game.addPlayer(line);
        }
        List<Player> winners = this.game.determineTheWinner();
        Assert.assertEquals(winners.size(), 2);
    }
}
