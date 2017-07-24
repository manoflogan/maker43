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
    public void testHighCardFileTest() throws Exception {
        try(InputStream is =
                ClassLoader.getSystemResourceAsStream("PairFile.txt");
                Scanner scanner = new Scanner(is);) {
            List<Player> winningPlayers =  this.threeCardPoker.playGame(scanner);
            Assert.assertEquals(winningPlayers.size(), 1);
            Player player = winningPlayers.get(0);
            Assert.assertEquals(player.getPlayerNumber(), 2);
            Assert.assertEquals(player.getHand().getHandWeight(), 60);
            List<Card> cards = player.getHand().getPlayingCards();
            Card firstCard = cards.get(0);
            Assert.assertEquals(firstCard.getCardWeight(), CardWeight.JACK);
            Assert.assertEquals(firstCard.getSuit(), Suit.CLUB);
        }
    }
}
