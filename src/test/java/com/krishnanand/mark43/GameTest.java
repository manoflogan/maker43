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
        Player first = winners.get(0);
        Player second = winners.get(1);
        Assert.assertEquals(first.getPlayerNumber(), 2);
        Assert.assertEquals(first.getHand().getHandWeight(), 70);
        Assert.assertEquals(first.getHand().getPlayingCards().get(0).getSuit(), Suit.HEARTS);
        Assert.assertEquals(first.getHand().getPlayingCards().get(0).getCardWeight(), CardWeight.TEN);
        Assert.assertEquals(second.getPlayerNumber(), 3);
        Assert.assertEquals(second.getHand().getHandWeight(), 70);
        Assert.assertEquals(second.getHand().getPlayingCards().get(0).getSuit(), Suit.CLUB);
        Assert.assertEquals(
            second.getHand().getPlayingCards().get(0).getCardWeight(), CardWeight.TEN);
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "1winnercards")
    public void testDetermineWinner_1Winners(String[] lines) throws Exception {
        for (String line: lines) {
            this.game.addPlayer(line);
        }
        List<Player> winners = this.game.determineTheWinner();
        Assert.assertEquals(winners.size(), 1);
        Player first = winners.get(0);
        Assert.assertEquals(first.getPlayerNumber(), 2);
        Assert.assertEquals(first.getHand().getHandWeight(), 60);
        Assert.assertEquals(first.getHand().getPlayingCards().get(0).getSuit(), Suit.CLUB);
        Assert.assertEquals(first.getHand().getPlayingCards().get(0).getCardWeight(), CardWeight.JACK);
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "highCardWinner")
    public void testDetermineWinner_HighPair(String[] lines) throws Exception {
        for (String line: lines) {
            this.game.addPlayer(line);
        }
        List<Player> winners = this.game.determineTheWinner();
        Assert.assertEquals(winners.size(), 1);
        Player first = winners.get(0);
        Assert.assertEquals(first.getPlayerNumber(), 0);
        Assert.assertEquals(first.getHand().getHandWeight(), 50);
        Assert.assertEquals(first.getHand().getPlayingCards().get(0).getSuit(), Suit.HEARTS);
        Assert.assertEquals(
            first.getHand().getPlayingCards().get(0).getCardWeight(), CardWeight.KING);
    }


}
