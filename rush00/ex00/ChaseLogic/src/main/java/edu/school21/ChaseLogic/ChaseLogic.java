package edu.school21.ChaseLogic;

import edu.school21.AStar.AStar;

import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.Map;

public class ChaseLogic {
    static final int ENEMY = 0;
    static final int PLAYER = 1;
    static final int WALL = 2;
    static final int GOAL = 3;
    static final int EMPTY = 4;

    AStar                   aStar;
    GameObjectInterface[][] gameObjects;
    char[][]                map;

    char[]                  objectChars;
    public ChaseLogic(char[] objectChars) {
        this.objectChars = objectChars;
    }

    public VectorInt getWay(GameObjectInterface[][] gameObjects, VectorInt start, VectorInt goal) {

        this.gameObjects = gameObjects;
        aStar = new AStar(this.gameObjects, objectChars);
        VectorInt dir = aStar.calculateDirection(start, goal);
        return dir;
    }

    public void printMap() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private void moveTo(GameObjectInterface object, VectorInt pos) {
        final int x = pos.getX();
        final int y = pos.getY();

        if (0 <= x && x < gameObjects.length
            && 0 <= y && y < gameObjects.length) {
            if (gameObjects[x][y] == null || gameObjects[x][y].getObjectChar() != objectChars[WALL]) {
                gameObjects[object.getX()][object.getY()] = null;
                object.setCoords(x, y);
                gameObjects[x][y] = object;
            }
        }
    }

//    private String
}