package edu.school21.game;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.map.Map;

@Parameters(separators = "=")
public class Game {
    @Parameter(names = "--enemiesCount")
    private int enemiesCount;
    @Parameter(names = "--wallsCount")
    private int wallsCount;
    @Parameter(names = "--size")
    private int size;
    @Parameter(names = "--profile")
    private String profile;
    private boolean devMode;
    private Map map;

    public Game() { }

    public void start() {

        checkArgs();
        createMap();
    }

    private void createMap() {
        map = new Map(devMode);
    }

    public static void exit(String errorReason) {
        System.err.println(errorReason);
        System.exit(-1);
    }

    private void checkArgs() {

        if (enemiesCount + wallsCount > size)
            exit("Error! Reduce the number of enemies and walls!");
        else if (!profile.equals("production") && !profile.equals("dev"))
            exit("Error! Unknown profile (production or dev only)!");
        else if (enemiesCount < 1)
            exit("Error! Enemies count must be > 0!");
        else if (wallsCount < 0)
            exit("Error! Walls count must be >= 0!");
        else if (size < 5 || size > 50)
            exit("Error! Size must be >= 5 and <= 50");

        devMode = profile.equals("dev");
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
