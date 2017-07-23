// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit Test for {@link Player}.
 */
public class PlayerTest {

    private Player player;

    @BeforeMethod
    public void setUp() throws Exception {
        this.player = new Player(0);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.player = null;
    }

    @Test
    public void testAddThreeOfKind() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("10", 'h'),
            Card.buildCard("10", 'd'),
            Card.buildCard("10", 'c'));
        this.assertPlayingHands("10c 10h 10d", expectedCard);
    }

    @Test
    public void testAddStraight() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("Q", 's'),
            Card.buildCard("J", 'd'),
            Card.buildCard("10", 'h'));
        this.assertPlayingHands("10h Jd Qs", expectedCard);
    }



    private void assertPlayingHands(String hand, List<Card> expected) {
        this.player.buildHand(hand);
        Hand h = this.player.getHand();
        List<Card> actualCards = h.getPlayingCards();
        Assert.assertEquals(expected, actualCards);
    }


}
