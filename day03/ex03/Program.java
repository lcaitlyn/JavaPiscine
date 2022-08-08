import java.io.File;
import java.util.LinkedList;

public class Program {
    public static final String ERROR = "Добавьте аргумент --threadsCount=SIZE (size > 0)";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println(ERROR);
            System.exit(-1);
        }

        int sizeThread = Integer.parseInt(args[0].replaceFirst("--threadsCount=", ""));

        if (sizeThread < 1) {
            System.err.println(ERROR);
            System.exit(-1);
        }

        File file = new File("files_urls.txt");

        if (!file.isFile() || !file.canRead()) {
            System.err.println("Добавьте файл files_urls.txt");
            System.exit(-1);
        }

        final LinkedList<String> urlQueue = new LinkedList<String>();

        Reader.addUrlDictionaryFromFile(file, urlQueue);

        if (urlQueue.isEmpty()) {
            System.err.println("Добавьте URLs в files_urls.txt");
            System.exit(-1);
        }

        for (int i = 0; i < sizeThread; i++) {
            new Thread(new MyRunnable(urlQueue)).start();
        }
    }
}
