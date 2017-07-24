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

    @Test
    public void testMultipleWinnersFileTest() throws Exception {
        this.runThreeGame("MultipleWinners.txt");
    }

    private void runThreeGame(String file) throws Exception {
        try(InputStream is =
            ClassLoader.getSystemResourceAsStream(file);
            Scanner scanner = new Scanner(is);) {
            List<Player> winningPlayers =  this.threeCardPoker.playGame(scanner);
            // Get the output from the file.
            String[] strs = new String[0];
            while(scanner.hasNext()) {
                String str = scanner.nextLine();
                if (str.isEmpty()) {
                    continue;
                }
                strs =  str.split( " ");
                break;
            }
            Assert.assertEquals(winningPlayers.size(), strs.length);
            this.threeCardPoker.displayResults(winningPlayers);
            for (int i = 0; i < strs.length; i++) {
                try {
                    int n = Integer.parseInt(strs[i], 10);
                    Player player = winningPlayers.get(i);
                    Assert.assertEquals(player.getPlayerNumber(), n);
                } catch (NumberFormatException nfe) {
                    // Ignore.
                }
            }


        }
    }
}
