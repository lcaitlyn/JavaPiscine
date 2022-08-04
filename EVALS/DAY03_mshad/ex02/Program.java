import java.util.Arrays;
import java.util.Random;

public class Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private static final String KEY_ARRAY_SIZE = "--arraySize=";
    private static final String KEY_THREADS_COUNT = "--threadsCount=";
    private static final int MAX_ARRAY_SIZE = 2_000_000;
    private static final int MAX_VALUE_ELEMENT = 1_001;
    private static final int MIN_VALUE_ELEMENT = -1_000;

    private static int arraySize;
    private static int threadsCount;
    private static int sumByThreads;
    private static int[] arrayNumbers;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            printError("Error arguments");
        }

        arraySize = getNumber(args[0], KEY_ARRAY_SIZE, MAX_ARRAY_SIZE);
        threadsCount = getNumber(args[1], KEY_THREADS_COUNT, arraySize);

        arrayNumbers = createArrayRandom();

        Thread[] threads = createThreads();
        for (int i = 0; i < threadsCount; i++) {
            threads[i].join();
        }

        System.out.print(ANSI_GREEN + "SUM: " + ANSI_RESET);
        System.out.println(Arrays.stream(arrayNumbers).sum());

        System.out.print(ANSI_GREEN + "SUM BY THREADS: " + ANSI_RESET);
        System.out.println(sumByThreads);
    }

    public static Thread[] createThreads() {
        int index = 0;
        int count = arraySize / threadsCount;

        Thread[] arrayThreads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount - 1; i++) {
            arrayThreads[i] = new Thread(new Runner(index, count));
            arrayThreads[i].setName("Thread " + (i + 1));
            arrayThreads[i].start();
            index += count;
        }

        arrayThreads[threadsCount - 1] = new Thread(new Runner(index, arraySize - index));
        arrayThreads[threadsCount - 1].setName("Thread " + threadsCount);
        arrayThreads[threadsCount - 1].start();
        return arrayThreads;
    }

    public static int countSumInInterval(int index, int count) {
        int result = 0;
        for (int i = index; i < index + count; i++) {
            result += arrayNumbers[i];
        }
        return result;
    }

    public static synchronized void countSumByThreads(int sumOneThread) {
        sumByThreads += sumOneThread;
    }

    public static int[] createArrayRandom() {
        int[] result = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            result[i] = random.nextInt(MAX_VALUE_ELEMENT - MIN_VALUE_ELEMENT) + MIN_VALUE_ELEMENT;
        }
        return result;
    }

    public static int getNumber(String source, String key, int max) {
        if (source.length() == 0 || !source.startsWith(key)) {
            printError("Error arguments");
        }
        int result = 0;
        try {
            result = Integer.parseInt(source.substring(source.indexOf("=") + 1));
            if (result <= 0 || result > max) {
                printError("Error number");
            }
        } catch (NumberFormatException ex) {
            printError("Error number");
        }
        return result;
    }

    public static void printError(String error) {
        System.err.println(error);
        System.exit(-1);
    }
}