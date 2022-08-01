import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Program {
    private static void errorfunc(String error) {
        System.err.println(error);
        System.exit(-1);
    }

    private static void mv(Path path, String sour, String dest) throws IOException {
        Path src = Paths.get(path + "/" + sour).normalize();
        Path dst = Paths.get(path + "/" + dest).normalize();
        if (Files.isRegularFile(src)) {
            if (Files.isDirectory(dst))
                dst = Paths.get(dst + "/" + src.getFileName()).normalize();
            Files.move(src, dst, REPLACE_EXISTING);
        } else
            System.out.println("Something wrong with path!");
    }

    private static void ls(Path path) throws IOException {
        List<Path> list = Files.walk(path, 1).collect(Collectors.toList());
        for (Path it : list) {
            if (it != path)
                System.out.println(it.getFileName() + " " + Files.size(it) + " KB");
        }
    }

    private static Path cd(Path path, String dest) {
        Path dst = Paths.get(path + "/" + dest).normalize();
        if (Files.exists(dst) && Files.isDirectory(dst)) {
            System.out.println(dst);
            return dst;
        } else {
            System.out.println("Incorrect path: " + dst);
            return path;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1 && args[0].startsWith("--current-folder=")) {
            Path path = Paths.get(args[0].substring(17));
            if (Files.exists(path)) {
                System.out.println(path);
                Scanner scanner = new Scanner(System.in);
                String line = "";
                while (!line.equals("exit")) {
                    line = scanner.nextLine();
                    String[] lines = line.split(" ");
                    if (lines[0].equals("mv"))
                        mv(path, lines[1], lines[2]);
                    else if (lines[0].equals("ls"))
                        ls(path);
                    else if (lines[0].equals("cd"))
                        path = cd(path, lines[1]);
                    else if (lines[0].equals("exit")) {
                        scanner.close();
                        System.exit(0);
                    }
                    else
                        System.out.println("Wrong input!");
                }
            } else
                errorfunc("Incorrect path: " + path);
        } else
            errorfunc("Wrong number of args!");
    }
}