// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.lang.reflect.Method;
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
        Assert.assertTrue(this.player.getHand().isThreeOfAKind());
    }

    @Test
    public void testAddStraight() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("Q", 's'),
            Card.buildCard("J", 'd'),
            Card.buildCard("10", 'h'));
        this.assertPlayingHands("10h Jd Qs", expectedCard);
        Assert.assertTrue(this.player.getHand().isStraight());
    }

    @Test
    public void testAddStraightFlush() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("Q", 's'),
            Card.buildCard("J", 's'),
            Card.buildCard("10", 's'));
        this.assertPlayingHands("10s Qs Js", expectedCard);
        Assert.assertTrue(this.player.getHand().isStraightFlush());
    }

    @Test
    public void testAddFlush() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("A", 'c'),
            Card.buildCard("8", 'c'),
            Card.buildCard("4", 'c'));
        this.assertPlayingHands("Ac 4c 8c", expectedCard);
        Assert.assertTrue(this.player.getHand().isFlush());
    }

    @Test
    public void testAddPair_FirstScenario() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("A", 's'),
            Card.buildCard("A", 'c'),
            Card.buildCard("4", 'h'));
        this.assertPlayingHands("Ac As 4h", expectedCard);
        Assert.assertTrue(this.player.getHand().isPair());
    }

    @Test
    public void testAddPair_SecondScenario() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("A", 's'),
            Card.buildCard("Q", 'd'),
            Card.buildCard("Q", 'c'));
        this.assertPlayingHands("Qc As Qd", expectedCard);
        Assert.assertTrue(this.player.getHand().isPair());
    }

    @Test
    public void testAddHighCard() throws Exception {
        List<Card> expectedCard = Arrays.asList(
            Card.buildCard("Q", 's'),
            Card.buildCard("7", 'd'),
            Card.buildCard("3", 'c'));
        this.assertPlayingHands("Qs 3c 7d", expectedCard);
        Assert.assertTrue(this.player.getHand().isHighCard());
    }

    private void assertPlayingHands(String hand, List<Card> expected)
            throws Exception {
        this.player.buildHand(hand);
        Hand h = this.player.getHand();
        List<Card> actualCards = h.getPlayingCards();
        Assert.assertEquals(expected, actualCards);
    }


}
