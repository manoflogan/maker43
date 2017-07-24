package com.krishnanand.mark43;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit Test of {@link Card}.
 */
public class CardTest {

    private Card card;


    @Test
    public void buildAceOfDiamonds() throws Exception {
        this.card = Card.buildCard("A", 'd');
        Assert.assertEquals(this.card.getCardWeight(), CardWeight.ACE);
        Assert.assertEquals(this.card.getSuit(), Suit.DIAMOND);
    }

    @Test
    public void buildKingOfHearts() throws Exception {
        this.card = Card.buildCard("K", 'h');
        Assert.assertEquals(this.card.getCardWeight(), CardWeight.KING);
        Assert.assertEquals(this.card.getSuit(), Suit.HEARTS);
    }

    @Test
    public void buildFiveOfClubs() throws Exception {
        this.card = Card.buildCard("5", 'c');
        Assert.assertEquals(this.card.getCardWeight(), CardWeight.FIVE);
        Assert.assertEquals(this.card.getSuit(), Suit.CLUB);
    }

    @Test
    public void buildNineOfCSpades() throws Exception {
        this.card = Card.buildCard("9", 's');
        Assert.assertEquals(this.card.getCardWeight(), CardWeight.NINE);
        Assert.assertEquals(this.card.getSuit(), Suit.SPADES);
    }
}
