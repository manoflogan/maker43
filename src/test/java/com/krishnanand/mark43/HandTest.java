package com.krishnanand.mark43;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@Hand}.
 */
public class HandTest {

    private Hand hand;

    @BeforeMethod
    public void setUp() throws Exception {
        this.hand = new Hand();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.hand = null;
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "highpair")
    public void testAddCards(Object[] args) throws Exception {
        this.addCards(args);
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "highpair")
    public void testHighPairCards(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(50,"isHighCard");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "threeOfAKind")
    public void testThreeOfAKindCards(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(90, "isThreeOfAKind");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "straight")
    public void testStraightCards(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(80, "isStraight");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "flush")
    public void testFlushCards(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(70, "isFlush");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "firstpair")
    public void testIsPair_FirstEntry(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(60, "isPair");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "secondpair")
    public void testIsPair_SecondEntry(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(60, "isPair");
    }

    @Test(dataProviderClass = StaticProviders.class, dataProvider = "straightflush")
    public void testStraightFlush(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards(100, "isStraightFlush", "isStraight", "isFlush");
    }

    /**
     * Checks the status of card
     *
     * @param args methods for which the output is to return {@true}
     * @throws Exception if methods are not accessible
     */
    private void checkStatusOfCards(int handWeight, String... args) throws Exception {
        Assert.assertEquals(handWeight, this.hand.getHandWeight());
        List<String> methodArgs = Arrays.asList(args);
        String[] methodNames =
            {"isStraightFlush", "isThreeOfAKind", "isStraight", "isFlush", "isPair", "isHighCard"};

        for (String methodName : methodNames) {
            Method m = this.hand.getClass().getDeclaredMethod(methodName);
            Boolean result = (Boolean) m.invoke(this.hand);
            if (methodArgs.contains(methodName)) {
                Assert.assertTrue(result);
            } else {
                Assert.assertFalse(result, "The method failed for " + methodName);
            }
        }
    }

    private void addCards(Object[] args) {
        for (Object arg : args) {
            Object[] handArgs = (Object[]) arg;
            String cardWeight = (String) handArgs[0];
            char suit = (char) handArgs[1];
            this.hand.addCard(cardWeight, suit);
        }
        Assert.assertEquals(3, this.hand.getPlayingCards().size());
    }
}
