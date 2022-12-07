package edu.school21.game;

import com.beust.jcommander.Parameter;
import edu.school21.ChaseLogic.ChaseLogic;
import edu.school21.ChaseLogic.VectorInt;
import edu.school21.Parameters.Parameters;
import edu.school21.objects.*;

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
    public static boolean           devMode = false;
    private Parameters              parameters;
    private GameObject[][]          gameObjects;
    private Player                  player;
    private Goal                    goal;
    private LinkedList<Enemy>       enemies = new LinkedList<>();
    private boolean                 canWin = false;
    private ChaseLogic              chaseLogic;
    private char[]                  objectChars;

    public Game() {
    }

    public void start() {
        checkArgs();

        initParameters();

        Scanner scanner = new Scanner(System.in);
        while (!canWin) {
            gameObjects = new GameObject[size][size];
            for (int i = 0; i < gameObjects.length; ++i)
                for (int j = 0; j < gameObjects[i].length; ++j) {
                    gameObjects[i][j] = new Empty();
                    gameObjects[i][j].setCoords(i, j);
                }
            this.player = (Player) putGameObjectInMapRandom(new Player());
            for (int i = 0; i < wallsCount; i++) {
                putGameObjectInMapRandom(new Wall());
            }
            this.goal = (Goal) putGameObjectInMapRandom(new Goal());
            canWin = checkPosition();
        }
        clearMapFromChecker();
        addEnemies();

        while (true) {
            printMap();
            movePlayerMenu();
            checkGameStatus();
            moveEnemies();
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (player.getX() == goal.getX() && player.getY() == goal.getY())
            Win();
//        for (GameObject enemy : enemies) {
//            if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
//                Lose();
//            }
//        }
    }

    private void enemyEat(Enemy enemy,  VectorInt pos) {
        gameObjects[enemy.getX()][enemy.getY()] = new Empty();
        gameObjects[enemy.getX()][enemy.getY()].setCoords(enemy.getX(), enemy.getY());
        gameObjects[pos.getX()][pos.getY()] = enemy;
        gameObjects[pos.getX()][pos.getY()].setCoords(pos.getX(), pos.getY());
    }
    private void moveEnemies() {

        if (devMode) {
            printMap();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                if (sc.nextLine().equals("8"))
                    break;
                System.out.println("Input '8' to move enemies");
            }
        }
        for (Enemy enemy : enemies){
            VectorInt pos = chaseLogic.getWay(gameObjects, enemy, player);
            if (pos != null) {
                if (gameObjects[pos.getX()][pos.getY()].getObjectChar() == Parameters.getEmptyChar()) {
                    gameObjects[enemy.getX()][enemy.getY()] = gameObjects[pos.getX()][pos.getY()];
                    gameObjects[enemy.getX()][enemy.getY()].setCoords(enemy.getX(), enemy.getY());
                    gameObjects[pos.getX()][pos.getY()] = enemy;
                    gameObjects[pos.getX()][pos.getY()].setCoords(pos.getX(), pos.getY());
                }
                else if (gameObjects[pos.getX()][pos.getY()].getObjectChar() == Parameters.getPlayerChar())
                    enemyEat(enemy, pos);
            }
            if (player.getX() == enemy.getX() && player.getY() == enemy.getY())
                Lose();
        }
    }
    private void addEnemies() {
        enemies = new LinkedList<>();
        GameObject enemy = new Enemy();

        for (int i = 0; i < enemiesCount; ++i)
            enemies.add((Enemy) putGameObjectInMapRandom(enemy));
    }

    private void clearMapFromChecker() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameObjects[i][j].getObjectColor().equals(Parameters.getEmptyColor()))
                    gameObjects[i][j] = new Empty();
            }
        }
    }

    private void printMap() {
        if (!devMode)
            System.out.print("\033[H\033[2J");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameObjects[i][j] == null)
                    gameObjects[i][j] = new Empty();
                gameObjects[i][j].draw();
            }
            System.out.println();
        }
    }

    private void initParameters() {
        parameters = new Parameters(profile);
        objectChars = new char[]{Parameters.getEnemyChar(), Parameters.getPlayerChar(), Parameters.getWallChar(), Parameters.getWallChar(), Parameters.getEnemyChar()};
        chaseLogic = new ChaseLogic(objectChars);
    }

    private GameObject putGameObjectInMapRandom(GameObject object){
        while (true) {
            try {
                int x = new Random().nextInt(size);
                int y = new Random().nextInt(size);

                if (gameObjects[x][y] == null || gameObjects[x][y].getObjectChar() == Parameters.getEmptyChar()) {
                    gameObjects[x][y] = object.getClass().newInstance();
                    gameObjects[x][y].setCoords(x, y);
                    return gameObjects[x][y];
                }
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
        }
    }

    private boolean checkPosition() {
        return (chaseLogic.getWay(gameObjects, player, goal) != null);
    }

    private boolean checkForGoalPosition(int x, int y) {
        return ((x + 1 < size && gameObjects[x + 1][y] == goal)
            && (y + 1 < size && gameObjects[x][y + 1] == goal)
            && (x - 1 >= 0 && gameObjects[x - 1][y] == goal)
            && (y - 1 >= 0 && gameObjects[x][y - 1] == goal));
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
            if (gameObjects[x][y + 1].getObjectChar() != Parameters.getWallChar()) {
                gameObjects[x][y + 1] = object;
                object.setCoords(x, y + 1);
                gameObjects[x][y] = new Empty();
            }
        }
    }

    private void moveDown(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (x + 1 < size) {
            if (gameObjects[x + 1][y].getObjectChar() != Parameters.getWallChar()) {
                gameObjects[x + 1][y] = object;
                object.setCoords(x + 1, y);
                gameObjects[x][y] = new Empty();
            }
        }
    }

    private void moveLeft(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (y - 1 > -1) {
            if (gameObjects[x][y - 1].getObjectChar() != Parameters.getWallChar()) {
                gameObjects[x][y - 1] = object;
                object.setCoords(x, y - 1);
                gameObjects[x][y] = new Empty();
            }
        }
    }

    private void moveUp(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (x - 1 > -1) {
            if (gameObjects[x - 1][y].getObjectChar() != Parameters.getWallChar()) {
                gameObjects[x - 1][y] = object;
                object.setCoords(x - 1, y);
                gameObjects[x][y] = new Empty();
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
