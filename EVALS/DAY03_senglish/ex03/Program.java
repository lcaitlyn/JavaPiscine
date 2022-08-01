package ex03;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program
{
    public static void main(String[] args)
    {
        final String fileName = "files_urls.txt";
        parseArgs(args);
        int threadNum = findNum(args[0], 15);
        Path path = Paths.get(fileName);
        Path absPath = path.toAbsolutePath();
        Links links = new Links(absPath);
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        for (int count = 0; count < links.getSize(); ++count)
        {
            executor.submit(new Downloader(links.urls.get(count), count + 1));
            links.urls.remove(links.urls.toString());
        }
        executor.shutdown();
    }
    static void parseArgs(String[] args)
    {
        if (args.length != 1 || !args[0].startsWith("--threadsCount="))
        {
            System.err.println("Program should contain one argument starting from --threadsCount= and number");
            System.exit(-1);
        }
    }
    static int findNum(String arg, int amount)
    {
        int i = 0;
        try
        {
            i = Integer.parseInt(arg.substring(amount));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        parseNum(i);
        return i;
    }
    static void parseNum(int threadNum)
    {
        if (threadNum <= 0)
        {
            System.err.println("Program should contain at least one element in array and at least one thread.");
            System.exit(-1);
        }
        if (threadNum > 2000000)
        {
            System.err.println("Given number of threads is too big.");
            System.exit(-1);
        }
    }
}
