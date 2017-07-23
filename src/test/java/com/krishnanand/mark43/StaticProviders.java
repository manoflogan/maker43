// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import org.testng.annotations.DataProvider;

/**
 * Basic Utility encapsulating data providers
 */
public class StaticProviders {

    @DataProvider(name="highpair")
    public static Object[][] getHighPairCards() {
        return new Object[][] {
            new Object[]{new Object[] {"2" , 'c'}, new Object[]{"A", 's'},
            new Object[]{"4", 'd'}}};
    }

    @DataProvider(name="threeOfAKind")
    public static Object[][] getThreeOfAKindCards() {
        return new Object[][] {
            new Object[]{new Object[] {"2" , 'c'}, new Object[]{"2", 's'},
                new Object[]{"2", 'd'}}};
    }

    @DataProvider(name="straight")
    public static Object[][] getStraightCards() {

        return new Object[][] {
            new Object[]{new Object[] {"T" , 'h'}, new Object[]{"Q", 'c'},
                new Object[]{"J", 'd'}}};
    }

    @DataProvider(name="flush")
    public static Object[][] getFlushCards() {
        // Ac 4c 8c
        return new Object[][] {
            new Object[]{new Object[] {"A" , 'c'}, new Object[]{"4", 'c'},
                new Object[]{"8", 'c'}}};
    }

    @DataProvider(name="firstpair")
    public static Object[][] getPairCards() {
        // Ac 4c 8c
        return new Object[][] {
            new Object[]{new Object[] {"4" , 'd'}, new Object[]{"4", 'c'},
                new Object[]{"A", 'h'}}};
    }

    @DataProvider(name="secondpair")
    public static Object[][] getPairCards2() {
        // Ac 4c 8c
        return new Object[][] {
            new Object[]{new Object[]{"A", 'h'}, new Object[] {"4" , 'd'}, new Object[]{"4", 'c'}}};
    }

    @DataProvider(name="straightflush")
    public static Object[][] getStraightFlushCards() {
        // 5h 3c 4d
        return new Object[][] {
            new Object[]{new Object[] {"4" , 'c'}, new Object[]{"3", 'c'},
                new Object[]{"5", 'c'}}};
    }

    @DataProvider(name="playercards")
    public static Object[][] getPlayerCards() {
        return new Object[][] {
            new Object[] {new String[] {"0 Qc Kc 4s", "1 Ah 2c Js", "2 3h 9h Th", "3 Tc 9c 3c"}}
        };
    }
}
