package edu.school21.objects;

import edu.school21.ChaseLogic.GameObjectInterface;

import java.awt.*;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;

public class GameObject implements GameObjectInterface {
    private final String PATTERN = " %c ";
    private char objectChar;
    private Color objectColor;
    private int x;
    private int y;

    public GameObject(char objectChar, Color objectColor) {
        this.objectChar = objectChar;
        this.objectColor = objectColor;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getObjectChar() {
        return objectChar;
    }

    public Color getObjectColor() {
        return objectColor;
    }

    public void draw() {
        System.out.print(colorize(String.format(PATTERN, objectChar), BACK_COLOR(objectColor.getRed(), objectColor.getGreen(), objectColor.getBlue())));
    }
}
