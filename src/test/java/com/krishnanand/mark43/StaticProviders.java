// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import org.testng.annotations.DataProvider;

/**
 * Basic Utility encapsulating data providers
 */
public class StaticProviders {

    @DataProvider(name="highpair")
    public static Object[][] getHighPairCard() {
        return new Object[][] {
            new Object[]{new Object[] {"2" , 'c'}, new Object[]{"A", 's'},
            new Object[]{"4", 'd'}}};
    }
}
