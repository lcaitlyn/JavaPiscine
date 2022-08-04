import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Program {
    private static final String ERROR = "Add args: --arraySize=SIZE_A --threadsCount=SIZE_B (A <= 2,000,000 && A >= B && A,B > 0)";
    private static int sizeArr;
    private static int sizeThread;

    public static void checkArgs() {
        if (sizeArr < 1 || sizeArr > 2000000 || sizeArr < sizeThread || sizeThread < 1) {
            System.err.println(ERROR);
            System.exit(-1);
        }
    }

    public static int [] createArray(int sizeArr) {
        Random random = new Random();
        int [] arr = new int[sizeArr];
        int sum = 0;

        for (int i = 0; i < sizeArr; i++) {
            arr[i] = random.nextInt() % 1000;
            sum += arr[i];
        }

        System.out.println("Sum: " + sum);
        return arr;
    }

    public static void printResult(BlockingQueue<Integer> queue) {
        int res = 0;

        for (int i = 0; i < sizeThread; i++) {
            try {
                res += queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum by threads: " + res);
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        if (args.length != 2) {
            System.err.println(ERROR);
            System.exit(-1);
        }

        sizeArr = Integer.parseInt(args[0].replaceFirst("--arraySize=", ""));
        sizeThread = Integer.parseInt(args[1].replaceFirst("--threadsCount=", ""));
        checkArgs();

        int [] arr = createArray(sizeArr);
        int [] size = new int[sizeThread];

        for (int i = 0; i < sizeArr; i++) {
            size[i % sizeThread]++;
        }

        int index = 0;
        for (int i = 0; i < sizeThread; i++) {
            new Thread(new MyRunnable(queue, arr, index, index + size[i])).start();
            index += size[i];
        }

        printResult(queue);
    }
}
