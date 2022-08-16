package edu.school21.printer.logic;

import java.awt.*;

public class Colors {
    public static Color getColor(String colorName) {
        if (colorName.equals("WHITE") || colorName.equals("white")) {
            return Color.WHITE;
        } else if (colorName.equals("BLACK") || colorName.equals("black")) {
            return Color.BLACK;
        } else if (colorName.equals("RED") || colorName.equals("red")) {
            return Color.RED;
        } else if (colorName.equals("GREEN") || colorName.equals("green")) {
            return Color.GREEN;
        } else if (colorName.equals("BLUE") || colorName.equals("blue")) {
            return Color.BLUE;
        } else if (colorName.equals("YELLOW") || colorName.equals("yellow")) {
            return Color.YELLOW;
        } else if (colorName.equals("CYAN") || colorName.equals("cyan")) {
            return Color.CYAN;
        }
        return null;
    }
}
