import java.util.Arrays;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        Menu menu = null;

        if (args.length == 2 && args[0].equals("--profile") && args[1].equals("=dev")) {
            menu = new Menu(true);
        } else if (args.length != 0) {
            System.err.println("Wrong argumets!");
            System.exit(-1);
        }
        else
            menu = new Menu(false);
        menu.start();
    }
}
