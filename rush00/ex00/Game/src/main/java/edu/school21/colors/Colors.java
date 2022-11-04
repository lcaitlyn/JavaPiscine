package edu.school21.colors;

import java.awt.*;

public class Colors {
    public static Color getColor(String colorName) {
        if (colorName.equalsIgnoreCase("white")) {
            return Color.WHITE;
        } else if (colorName.equalsIgnoreCase("black")) {
            return Color.BLACK;
        } else if (colorName.equalsIgnoreCase("red")) {
            return Color.RED;
        } else if (colorName.equalsIgnoreCase("green")) {
            return Color.GREEN;
        } else if (colorName.equalsIgnoreCase("blue")) {
            return Color.BLUE;
        } else if (colorName.equalsIgnoreCase("yellow")) {
            return Color.YELLOW;
        } else if (colorName.equalsIgnoreCase("cyan")) {
            return Color.CYAN;
        } else if (colorName.equalsIgnoreCase("MAGENTA"))
            return Color.MAGENTA;
        return null;
    }
}
