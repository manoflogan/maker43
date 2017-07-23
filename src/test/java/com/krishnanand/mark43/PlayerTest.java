// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
}
