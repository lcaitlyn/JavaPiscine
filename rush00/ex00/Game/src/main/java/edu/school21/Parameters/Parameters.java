package edu.school21.Parameters;

import edu.school21.colors.Colors;

import java.awt.*;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Parameters {
    private char enemyChar;
    private char playerChar;
    private char wallChar;
    private char goalChar;
    private char emptyChar;
    private Color enemyColor;
    private Color playerColor;
    private Color wallColor;
    private Color goalColor;
    private Color emptyColor;
    private final String PROPERTIES_PATH = "/application-%s.properties";

    // всю эту тему можно сделать легче изучив класс Properties
    public Parameters(String mode) {
        try {
            File file = new File(Parameters.class.getResource(String.format(PROPERTIES_PATH, mode)).toURI());

            if (!file.exists())
                throw new RuntimeException("Error! File 'application-*.properties' not exists!");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                fillAttribute(scanner.nextLine());
            }

            checkArgs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillAttribute(String s) {
        if (s.toLowerCase().contains("char"))
            getCharFromString(s.split("="));
        else if (s.toLowerCase().contains("color"))
            getColorFromString(s.split("="));
    }

    private void getCharFromString(String [] args) {
        char c = (args.length == 2) ? args[1].trim().charAt(0) : ' ';

        switch (args[0].trim().toLowerCase()) {
            case "enemy.char":
                setEnemyChar(c);
                return;
            case "player.char":
                setPlayerChar(c);
                return;
            case "wall.char":
                setWallChar(c);
                return;
            case "goal.char":
                setGoalChar(c);
                return;
            case "empty.char":
                setEmptyChar(c);
        }
    }

    private void getColorFromString(String [] args) {
        switch (args[0].trim().toLowerCase()) {
            case "enemy.color":
                setEnemyColor(Colors.getColor(args[1].trim()));
                return;
            case "player.color":
                setPlayerColor(Colors.getColor(args[1].trim()));
                return;
            case "wall.color":
                setWallColor(Colors.getColor(args[1].trim()));
                return;
            case "goal.color":
                setGoalColor(Colors.getColor(args[1].trim()));
                return;
            case "empty.color":
                setEmptyColor(Colors.getColor(args[1].trim()));
        }
    }

    private void checkArgs() {
        HashSet<Character> characterHashSet = new HashSet<>();
        HashSet<Color> colorHashSet = new HashSet<>();

        characterHashSet.add(enemyChar);
        characterHashSet.add(playerChar);
        characterHashSet.add(wallChar);
        characterHashSet.add(goalChar);
        characterHashSet.add(emptyChar);
        colorHashSet.add(enemyColor);
        colorHashSet.add(playerColor);
        colorHashSet.add(wallColor);
        colorHashSet.add(goalColor);
        colorHashSet.add(emptyColor);

        if (characterHashSet.size() != 5 || colorHashSet.size() != 5)
            throw new RuntimeException("Error! 'application.properties' stores the duplicate characters or colors!");
    }


    public void setEnemyChar(char enemyChar) {
        this.enemyChar = enemyChar;
    }

    public void setPlayerChar(char playerChar) {
        this.playerChar = playerChar;
    }

    public void setWallChar(char wallChar) {
        this.wallChar = wallChar;
    }

    public void setGoalChar(char goalChar) {
        this.goalChar = goalChar;
    }

    public void setEmptyChar(char emptyChar) {
        this.emptyChar = emptyChar;
    }

    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void setWallColor(Color wallColor) {
        this.wallColor = wallColor;
    }

    public void setGoalColor(Color goalColor) {
        this.goalColor = goalColor;
    }

    public void setEmptyColor(Color emptyColor) {
        this.emptyColor = emptyColor;
    }

    public char getEnemyChar() {
        return enemyChar;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public char getWallChar() {
        return wallChar;
    }

    public char getGoalChar() {
        return goalChar;
    }

    public char getEmptyChar() {
        return emptyChar;
    }

    public Color getEnemyColor() {
        return enemyColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Color getWallColor() {
        return wallColor;
    }

    public Color getGoalColor() {
        return goalColor;
    }

    public Color getEmptyColor() {
        return emptyColor;
    }
}
