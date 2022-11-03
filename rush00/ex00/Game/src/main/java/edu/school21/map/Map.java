package edu.school21.map;

import com.diogonunes.jcolor.Attribute;
import edu.school21.game.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private char enemyChar;
    private char playerChar;
    private char wallChar;
    private char goalChar;
    private char emptyChar;
    private Attribute enemyColor;
    private Attribute playerColor;
    private Attribute wallColor;
    private Attribute goalColor;
    private Attribute emptyColor;
    private final String DEV_PROPERTIES_PATH = "./src/main/resources/application-dev.properties";
    private final String PRODUCTION_PROPERTIES_PATH = "./src/main/resources/application-production.properties";

    public Map(boolean devMode) {
        try {
            File file = (devMode) ? new File(DEV_PROPERTIES_PATH) : new File(PRODUCTION_PROPERTIES_PATH);

            if (!file.exists())
                Game.exit("Error! File .properties not exists!");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                for (String s : scanner.nextLine().split("="))
                    System.out.println(s.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillAttributes(String s) {
        
    }

}
