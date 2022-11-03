package edu.school21.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.game.Game;

public class Program {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Error! Add Arguments: --enemiesCount=10 --wallsCount=10 --size=30 --profile=production/dev");
            System.exit(-1);
        }

        Game game = new Game();
        JCommander.newBuilder().addObject(game).build().parse(args);

        game.start();
    }
}
