package com.krishnanand.mark43;

import java.io.InputStream;
import java.util.Collections;
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
    public void testPairFileTest() throws Exception {
        this.runThreeGame("poker/PairFile.txt");
    }

    @Test
    public void testHighCardFileTest() throws Exception {
        this.runThreeGame("poker/HighCard.txt");
    }

    @Test
    public void testMultipleWinnersFileTest() throws Exception {
        this.runThreeGame("poker/MultipleWinners.txt");
    }

    @Test
    public void testLoadContentsFromFile() throws Exception {
        String filename =
            this.getClass().getClassLoader().getResource("poker/HighCard.txt").getFile();
        try(InputStream is = this.threeCardPoker.loadContentsFromFilePath(filename);) {
            Assert.assertNotNull(is);
        }
    }

    @Test(expectedExceptions = PokerFileNotFoundException.class,
        expectedExceptionsMessageRegExp = "^The poker file  was not found.$")
    public void testLoadContentsFromFile_throwsException() throws Exception {
        this.threeCardPoker.loadContentsFromFilePath("");
    }

    @Test
    public void testStartTheGame() throws Exception {
        String filePath = new StringBuilder(System.getProperty("user.dir")).append("/")
            .append("src/test/resources/poker/HighCard.txt").toString();
        Assert.assertTrue(this.threeCardPoker.startTheGame(filePath));
    }

    @Test(expectedExceptions = PokerFileNotFoundException.class,
        expectedExceptionsMessageRegExp = "^The poker file test was not found.$")
    public void testStartTheGame_throwsException() throws Exception {
        this.threeCardPoker.startTheGame("test");
    }

    @Test
    public void testIsValid() throws Exception {
        String[] filePath = new String[] {"src/test/resources"};
        Assert.assertTrue(ThreeCardPoker.isValid(filePath));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "^The implementation requires a file directory as an argument.$")
    public void testIsValid_FailsForNullArray() throws Exception {
        Assert.assertTrue(ThreeCardPoker.isValid(null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
        expectedExceptionsMessageRegExp = "^The implementation requires a file directory as an argument.$")
    public void testIsValid_FailsForEmptyArray() throws Exception {
        Assert.assertTrue(ThreeCardPoker.isValid(new String[0]));
    }

    @Test
    public void testListFiles() throws Exception {
        String filePath = new StringBuilder(System.getProperty("user.dir")).append("/")
            .append("src/test/resources").toString();
        List<String> allFiles = ThreeCardPoker.getFileListFromDirectory(filePath);
        Collections.sort(allFiles);
        Assert.assertEquals(
            allFiles.toArray(new String[0]),
            new String[] {"poker", "testng.xml"});
    }

    @Test
    public void testInitiate() throws Exception {
        Assert.assertTrue(ThreeCardPoker.initiate(new String[]{"src/test/resources/poker"}));

    }


    private void runThreeGame(String file) throws Exception {
        try(InputStream is = ClassLoader.getSystemResourceAsStream(file);
            Scanner scanner = new Scanner(is);) {
            List<Player> winningPlayers =  this.threeCardPoker.playGame(scanner);
            // Get the output from the file.
            String[] strs = new String[0];
            while(scanner.hasNext()) {
                String str = scanner.nextLine();
                if (str.isEmpty()) {
                    continue;
                }
                strs =  str.split( " ");
                break;
            }
            Assert.assertEquals(winningPlayers.size(), strs.length);
            this.threeCardPoker.displayResults(winningPlayers);
            for (int i = 0; i < strs.length; i++) {
                try {
                    int n = Integer.parseInt(strs[i], 10);
                    Player player = winningPlayers.get(i);
                    Assert.assertEquals(player.getPlayerNumber(), n);
                } catch (NumberFormatException nfe) {
                    // Ignore.
                }
            }
        }
    }
}
