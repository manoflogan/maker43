// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@ThreeCardPoker}.
 */
public class ThreeCardPokerTest {

    private ThreeCardPoker threeCardPoker;

    @BeforeMethod
    public void setUp() throws Exception {
        this.threeCardPoker = new ThreeCardPoker();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.threeCardPoker = null;
    }

    @Test
    public void testPairFileTest() throws Exception {
        this.runThreeGame("PairFile.txt");
    }

    @Test
    public void testHighCardFileTest() throws Exception {
        this.runThreeGame("HighCard.txt");
    }

    private void runThreeGame(String file) throws Exception {
        try(InputStream is =
            ClassLoader.getSystemResourceAsStream(file);
            Scanner scanner = new Scanner(is);) {
            List<Player> winningPlayers =  this.threeCardPoker.playGame(scanner);
            // Get the output from the file.
            int n = 100; // Arbitarily large number.
            while(scanner.hasNext()) {
                try {
                    n = Integer.parseInt(scanner.nextLine(), 10);
                    break;
                } catch (NumberFormatException nfe) {
                    // Ignore.
                }
            }
            Assert.assertEquals(winningPlayers.size(), 1);
            Player player = winningPlayers.get(0);
            Assert.assertEquals(player.getPlayerNumber(), n);
        }
    }
}