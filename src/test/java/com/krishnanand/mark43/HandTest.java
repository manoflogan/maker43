// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.beans.PropertyDescriptor;
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
    public void testHighPair(Object[] args) throws Exception {
        this.addCards(args);
        this.hand.calculateHandStatus();
        this.checkStatusOfCards("isHighCard");
    }

    /**
     * Checks the status of card
     *
     * @param args methods for which the output is to return {@true}
     * @throws Exception if methods are not accessible
     */
    private void checkStatusOfCards(String... args) throws Exception {
        List<String> methodArgs = Arrays.asList(args);
        String[] methodNames =
            {"isStraightFlush", "isThreeOfAKind", "isStraight", "isFlush", "isPair", "isHighCard"};

        for (String methodName : methodNames) {
            Method m = this.hand.getClass().getDeclaredMethod(methodName);
            Boolean result = (Boolean) m.invoke(this.hand);
            if (methodArgs.contains(methodName)) {
                Assert.assertTrue(result);
            } else {
                Assert.assertFalse(result);
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
