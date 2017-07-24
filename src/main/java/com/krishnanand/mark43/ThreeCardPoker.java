// Copyright 2017 ManOf Logan. All Rights Reserved.
package com.krishnanand.mark43;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * An entry class representing the simulation of the three card poker.
 *
 * <p>The implementation requires the command line argument representing the input directory. The
 * implementation will iterate through each file of the directory to run the poker game.
 *
 * <p>To play the game, the following command must be executed
 * {@code java ThreeCard <filedirectory>} or {@code java -jar <jar-file> test}.
 */
public class ThreeCardPoker {

    public static void main(String[] args) {
        initiate(args);
    }


    /**
     * Checks if the command line arguments are available
     *
     * @param commandlineArgs array of command line arguments
     * @return {@code true} if the arguments are not null; false otherwise
     */
    static boolean isValid(String[] commandlineArgs) {
        if (commandlineArgs == null || commandlineArgs.length == 0) {
            throw new IllegalArgumentException(
                "The implementation requires a file directory as an argument.");
        }
        return true;
    }

    /**
     * Returns the list of files from a fully qualified directory path.
     *
     * @param file fully qualified directory path
     * @return list of file names
     */
    static List<String> getFileListFromDirectory(String file) {
        File f = new File(file);
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files == null || files.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.asList(files);
        }
        return Collections.emptyList();
    }

    /**
     * Initiates the game.
     *
     * @param args array of directory paths containing test files relative to the location of the
     *             archive
     * @return {@code true} if the game can be initiated
     * @exception GameNotPlayedException if the arguments are invalid
     */
     static boolean initiate(String[] args) {
        if (isValid(args)) {

            List<String> filePaths = new ArrayList<>();
            for (String directoryPath : args) {
                String filePath = new StringBuilder(System.getProperty("user.dir")).append("/")
                    .append(directoryPath).toString();
                List<String> allFiles = getFileListFromDirectory(filePath);
                for (String file : allFiles) {
                    filePaths.add(filePath + "/" + file);
                }
            }
            ThreeCardPoker threeCardPoker = new ThreeCardPoker();
            for (String filePath : filePaths) {
                threeCardPoker.startTheGame(filePath);
            }
            return true;
        }
        throw new GameNotPlayedException();
    }

    /**
     * Starts the game.
     *
     * @param filePath path relative to the file
     * @return {@code true} if the game is completed.
     */
    boolean startTheGame(String filePath) {
        // The assumption is that the directory
        try(InputStream is = this.loadContentsFromFilePath(filePath);
            Scanner scanner = new Scanner(is);) {
            List<Player> winningPlayers = this.playGame(scanner);
            this.displayResults(winningPlayers);
            return true;
        } catch (IOException e) {
            throw new PokerFileNotFoundException(filePath, e);
        }
    }

    /**
     * Generates the input stream of the file content represented by the file path.
     *
     * <p>The function is not responsible for closing the inputstream after it is no longer needed.
     * That responsibility falls on invoking function.
     *
     * @param filePath fully qualified path of the file to be read
     * @return input stream
     * @exception  PokerFileNotFoundException if the file can not be found any reason
     */
    InputStream loadContentsFromFilePath(String filePath) {
        try {
            return new BufferedInputStream(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new PokerFileNotFoundException(filePath, e);
        }
    }

    /**
     * Calls the game.
     *
     * <p>The input is always in the following format as given below:
     *  <ul>
     *     <li>The first line is always the number of players participating.</li>
     *     <li>The next "N" lines represent the player number, and their hands.</li>
     * </ul>
     *
     * <p>The sample input value is given below:
     * {@code 3
         0 2c As 4d
         1 Kd 5h 6c
         2 Jc Jd 9s}.
     * </p>
     *
     * <p>In this example, input on the first line {@code 3} represents the number of players
     * that will be playing the game. This is followed by {@code 0 2c As 4d}, in which the first
     * {@code 0} represents the player number or index, the next substring {@code 2c As 4d}
     * represents the player's hand. In this example, the player has the two of clubs, Ace of
     * spades, and four of diamonds. Similarly player 1 and player 2 have the king of diamonds, five
     * of hearts, and 6 of clubs, and Jack of clubs, Jack of diamonds, and nine of spaces
     * respectively.
     *
     * <p>The implementation is not responsible for the closing of any IO resources. That is the
     * responsibility of the invoking function
     *
     * @param scanner scanner to read values
     * @return list of objects representing the winning player
     */
    List<Player> playGame(Scanner scanner) {
        boolean isValidInputFound = false;
        // The input files have certain on top which need to be ignored.
        // Continue until the value input is reached.
        int numberOfPlayers = 0;
        while (!isValidInputFound) {
            try {
                String n = scanner.nextLine();
                numberOfPlayers = Integer.parseInt(n);
                isValidInputFound = true;
                break;
            } catch (NumberFormatException ime) {
                // Invalid input. Ignore.
                continue;
            }
        }

        // Extract the number of players

        if (numberOfPlayers == 0) {
            throw new IllegalArgumentException("No players have been selected");
        }
        assert numberOfPlayers < 24 :
            new StringBuilder("The number of players will always be less than 24. ").append(
                "You have selected").append(numberOfPlayers).toString();

        Game game = new Game();
        int counter = 0;
        while (counter < numberOfPlayers) {
            String line = scanner.nextLine();
            game.addPlayer(line);
            counter ++;
        }
        List<Player> players =  game.determineTheWinner();
        return Collections.unmodifiableList(players);
    }

    /**
     * Display the player numbers of a list of players.
     *
     * @param players list of players
     */
    void displayResults(List<Player> players) {
        int s = players.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s; i++) {
            sb.append(players.get(i).getPlayerNumber());
            if (i < (s - 1)) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }


}
