package edu.school21.game;

import com.beust.jcommander.Parameter;
import edu.school21.ChaseLogic.ChaseLogic;
import edu.school21.Parameters.Parameters;
import edu.school21.objects.GameObject;

import java.awt.*;
import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;

@com.beust.jcommander.Parameters(separators = "=")
public class Game {
    @Parameter(names = "--enemiesCount")
    private int enemiesCount;
    @Parameter(names = "--wallsCount")
    private int wallsCount;
    @Parameter(names = "--size")
    private int size;
    @Parameter(names = "--profile")
    private String profile;
    public static boolean devMode = false;
    private Parameters parameters;
    private GameObject [][] gameObjects;
    private GameObject enemy;
    private GameObject player;
    private GameObject wall;
    private GameObject goal;
    private GameObject empty;
    private boolean canWin = false;
    private LinkedList<GameObject> enemies = new LinkedList<>();
    private ChaseLogic chaseLogic;

    public Game() { }

    public void start() {
        checkArgs();

        initParameters();

        Scanner scanner = new Scanner(System.in);
        while (!canWin) {
            LinkedList<GameObject> stepObjects = new LinkedList<>();
            gameObjects = new GameObject[size][size];
            putGameObjectInMapRandom(player);
            for (int i = 0; i < wallsCount; i++) {
                putGameObjectInMapRandom(wall);
            }
            putGameObjectInMapRandom(goal);
            stepObjects.add(player);
            checkPosition(stepObjects);
        }
        clearMapFromChecker();
        addEnemies();

        while (true) {
            printMap();
            movePlayerMenu();
            checkGameStatus();
            chaseLogic.getWay(gameObjects);
        }
    }

    private void checkGameStatus() {
        if (player.getX() == goal.getX() && player.getY() == goal.getY())
            Win();
        for (GameObject enemy : enemies) {
            System.out.println("Player: " + player.getX() + player.getY() + " Enemy: " + enemy.getX() + enemy.getY());
            if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
                Lose();
            }
        }
    }

    private void addEnemies() {
        for (int i = 0; i < enemiesCount; i++)
            putGameObjectInMapRandom(enemy);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameObjects[i][j] == enemy)
                    enemies.add(gameObjects[i][j]);
            }
        }
    }

    private void clearMapFromChecker() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameObjects[i][j] != null && gameObjects[i][j].getObjectColor().equals(parameters.getEmptyColor()))
                    gameObjects[i][j] = null;
            }
        }
    }

    private void printMap() {
        if (!devMode)
            System.out.print("\033[H\033[2J");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameObjects[i][j] == null)
                    empty.draw();
                else
                    gameObjects[i][j].draw();
            }
            System.out.println();
        }
    }

    private void initParameters() {
        parameters = new Parameters(profile);

        enemy = new GameObject(parameters.getEnemyChar(), parameters.getEnemyColor());
        player = new GameObject(parameters.getPlayerChar(), parameters.getPlayerColor());
        wall = new GameObject(parameters.getWallChar(), parameters.getWallColor());
        goal = new GameObject(parameters.getGoalChar(), parameters.getGoalColor());
        empty = new GameObject(parameters.getEmptyChar(), parameters.getEmptyColor());

        chaseLogic = new ChaseLogic(enemy, player, wall, goal, empty);
    }

    private void putGameObjectInMapRandom(GameObject object) {
        while (true) {
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);

            if (gameObjects[x][y] == null) {
                gameObjects[x][y] = object;
                object.setCoords(x, y);
                break;
            }
        }
    }

    private boolean checkPosition(LinkedList<GameObject> stepObjects) {
        if (stepObjects.size() == 0)
            return false;

        char c = stepObjects.get(0).getObjectChar();
        if (stepObjects.get(0) == player) {
            c = '1';
        } else
            c++;

        LinkedList<GameObject> newStepObjects = new LinkedList<>();
        for (GameObject o : stepObjects) {

            int x = o.getX();
            int y = o.getY();

            GameObject object = new GameObject(c, parameters.getEmptyColor());

            if (checkForGoalPosition(x, y)) {
                canWin = true;
                return true;
            }

            if (x + 1 < size && gameObjects[x + 1][y] == null) {
                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
                gameObject.setCoords(x + 1, y);
                gameObjects[x + 1][y] = gameObject;
                newStepObjects.add(gameObject);
            }
            if (y + 1 < size && gameObjects[x][y + 1] == null) {
                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
                gameObject.setCoords(x , y + 1);
                gameObjects[x][y + 1] = gameObject;
                newStepObjects.add(gameObject);
            }
            if (x - 1 >= 0 && gameObjects[x - 1][y] == null) {
                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
                gameObject.setCoords(x - 1, y);
                gameObjects[x - 1][y] = gameObject;
                newStepObjects.add(gameObject);
            }
            if (y - 1 >= 0 && gameObjects[x][y - 1] == null) {
                GameObject gameObject = new GameObject(c, parameters.getEmptyColor());
                gameObject.setCoords(x , y - 1);
                gameObjects[x][y - 1] = gameObject;
                newStepObjects.add(gameObject);
            }
//            printMap();
        }

        checkPosition(newStepObjects);
        return false;
    }

    private boolean checkForGoalPosition(int x, int y) {
        if (x + 1 < size && gameObjects[x + 1][y] == goal)
            return true;
        if (y + 1 < size && gameObjects[x][y + 1] == goal)
            return true;
        if (x - 1 >= 0 && gameObjects[x - 1][y] == goal)
            return true;
        if (y - 1 >= 0 && gameObjects[x][y - 1] == goal)
            return true;
        return false;
    }

    private void checkArgs() {
        if (enemiesCount + wallsCount > size * (size / 2))
            throw new RuntimeException("Error! Reduce the number of enemies and walls!");
        else if (!profile.equals("production") && !profile.equals("dev"))
            throw new RuntimeException("Error! Unknown profile (production or dev only)!");
        else if (enemiesCount < 1)
            throw new RuntimeException("Error! Enemies count must be > 0!");
        else if (wallsCount < 0)
            throw new RuntimeException("Error! Walls count must be >= 0!");
        else if (size < 5 || size > 50)
            throw new RuntimeException("Error! Size must be >= 5 and <= 50");

        devMode = profile.equals("dev");
    }

    public void movePlayerMenu() {
        while (true) {
            String way = new Scanner(System.in).nextLine();
            if (way.equalsIgnoreCase("w")) {
                moveUp(player);
                return;
            }
            else if (way.equalsIgnoreCase("a")) {
                moveLeft(player);
                return;
            }
            else if (way.equalsIgnoreCase("s")) {
                moveDown(player);
                return;
            }
            else if (way.equalsIgnoreCase("d")) {
                moveRight(player);
                return;
            }
            else if (way.equalsIgnoreCase("9"))
                Lose();
            else
                System.out.println("Only \"WASD\" or 9 for surrender");
        }
    }

    private void moveRight(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (y + 1 < size) {
            if (gameObjects[x][y + 1] != wall) {
                gameObjects[x][y + 1] = object;
                object.setCoords(x, y + 1);
                gameObjects[x][y] = null;
            }
        }
    }

    private void moveDown(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (x + 1 < size) {
            if (gameObjects[x + 1][y] != wall) {
                gameObjects[x + 1][y] = object;
                object.setCoords(x + 1, y);
                gameObjects[x][y] = null;
            }
        }
    }

    private void moveLeft(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (y - 1 > -1) {
            if (gameObjects[x][y - 1] != wall) {
                gameObjects[x][y - 1] = object;
                object.setCoords(x, y - 1);
                gameObjects[x][y] = null;
            }
        }
    }

    private void moveUp(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (x - 1 > -1) {
            if (gameObjects[x - 1][y] != wall) {
                gameObjects[x - 1][y] = object;
                object.setCoords(x - 1, y);
                gameObjects[x][y] = null;
            }
        }
    }

    public void Lose() {
        printMap();
        System.out.print(colorize( " You lost! ", BACK_COLOR(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue())));
        System.exit(0);
    }

    public void Win() {
        printMap();
        System.out.print(colorize(" You won! ", BACK_COLOR(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue())));
        System.exit(0);
    }
    @Override
    public String toString() {
        return "Game{" +
                "enemiesCount=" + enemiesCount +
                ", wallsCount=" + wallsCount +
                ", size=" + size +
                ", profile='" + profile + '\'' +
                '}';
    }
}
