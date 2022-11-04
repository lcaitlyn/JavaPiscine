package edu.school21.ChaseLogic;

import java.util.LinkedList;
import java.util.Map;

public class ChaseLogic {
    GameObjectInterface enemy;
    GameObjectInterface player;
    GameObjectInterface wall;
    GameObjectInterface goal;
    GameObjectInterface empty;
    char [][] map;

    public ChaseLogic(GameObjectInterface enemy, GameObjectInterface player, GameObjectInterface wall, GameObjectInterface goal, GameObjectInterface empty) {
        this.enemy = enemy;
        this.player = player;
        this.wall = wall;
        this.goal = goal;
        this.empty = empty;
    }

    public String getWay(GameObjectInterface [][] gameObjects) {
        // да да время коментов
        // ну че что нужно замутить. нужно сделать проходку + пофиксить врагов

        map = new char[gameObjects.length][gameObjects.length];
        for (int i = 0; i < gameObjects.length; i++) {
            for (int j = 0; j < gameObjects.length; j++) {
                if (gameObjects[i][j] == null)
                    map[i][j] = ' ';
                else if (gameObjects[i][j].getObjectChar() == enemy.getObjectChar())
                    map[i][j] = 'E';
                else if (gameObjects[i][j].getObjectChar() == player.getObjectChar())
                    map[i][j] = 'P';
                else if (gameObjects[i][j].getObjectChar() == wall.getObjectChar())
                    map[i][j] = 'W';
                else if (gameObjects[i][j].getObjectChar() == goal.getObjectChar())
                    map[i][j] = 'G';
            }
        }
        printMap();
        return null;
    }

    public void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

//    private boolean checkPosition(LinkedList<GameObject> stepObjects) {
//        if (stepObjects.size() == 0)
//            return false;
//
//        char c = stepObjects.get(0).getObjectChar();
//        if (stepObjects.get(0) == player) {
//            c = '1';
//        } else
//            c++;
//
//        LinkedList<GameObject> newStepObjects = new LinkedList<>();
//        for (GameObject o : stepObjects) {
//
//            int x = o.getX();
//            int y = o.getY();
//
//            GameObject object = new GameObject(c, parameters.getEmptyColor());
//
//            if (checkForGoalPosition(x, y)) {
//                canWin = true;
//                return true;
//            }
//
//            if (x + 1 < size && gameObjects[x + 1][y] == null) {
//                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
//                gameObject.setCoords(x + 1, y);
//                gameObjects[x + 1][y] = gameObject;
//                newStepObjects.add(gameObject);
//            }
//            if (y + 1 < size && gameObjects[x][y + 1] == null) {
//                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
//                gameObject.setCoords(x , y + 1);
//                gameObjects[x][y + 1] = gameObject;
//                newStepObjects.add(gameObject);
//            }
//            if (x - 1 >= 0 && gameObjects[x - 1][y] == null) {
//                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
//                gameObject.setCoords(x - 1, y);
//                gameObjects[x - 1][y] = gameObject;
//                newStepObjects.add(gameObject);
//            }
//            if (y - 1 >= 0 && gameObjects[x][y - 1] == null) {
//                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
//                gameObject.setCoords(x , y - 1);
//                gameObjects[x][y - 1] = gameObject;
//                newStepObjects.add(gameObject);
//            }
////            printMap();
//        }
//        checkPosition(newStepObjects);
//        return false;
//    }
}