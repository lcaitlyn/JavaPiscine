import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Microshell {
    public final String ERROR = "Wrong argument! Try:\nls\nmv WHAT WHERE\ncd WHERE\nexit";
    private String cwd;

    public Microshell(String cwd) {
        this.cwd = cwd;
    }

    private void mv (String what, String where) {
        Path from = Paths.get(cwd).resolve(Paths.get(what));
        Path to = Paths.get(cwd).resolve(Paths.get(where));

        if (!Files.exists(Paths.get(cwd).resolve(Paths.get(what)))) {
            System.err.println("mv: " + what + ": No such file or directory");
            return;
        }

        if (Files.isDirectory(to)) {
            to = Paths.get(to.toString() + "/" + new File(what).getName());
        }

        try {
            Files.move(from, to, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cd (String newDir) {
        Path current = Paths.get(cwd);
        Path destination = current.resolve(Paths.get(newDir));

        if (!Files.exists(destination) || !Files.isDirectory(destination)) {
            System.err.println("cd: no such file or directory: " + Paths.get(newDir).toString());
        } else {
            this.cwd = destination.toString();
        }
    }

    private void ls () {
        for (File f : new File(cwd).listFiles()) {
            System.out.println(f.getName() + " " + f.length() / 1024 + " KB");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        String str;


        while (true) {
            str = scanner.nextLine().trim();

            if (str.length() == 0) {
                System.err.println(ERROR);
            }

            String [] args = str.split(" ");

            if (args.length == 0) {
                System.err.println(ERROR);
            } else if (args[0].equals("ls")) {
                ls();
            } else if (args[0].equals("cd") && args.length == 2) {
                cd(args[1]);
            } else if (args[0].equals("mv") && args.length == 3) {
                mv(args[1], args[2]);
            } else if (args[0].equals("exit")) {
                System.exit(0);
            } else {
                System.err.println(ERROR);
            }
        }
    }
}
