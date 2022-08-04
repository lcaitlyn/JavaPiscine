public class Program implements Runnable {
    private int count;

    private String message;

    private static int cycleCount = 0;

    public static void main(String[] args) throws InterruptedException {
        if (!isCorrectString(args)) {
            System.out.println("Please, enter valid arguments:\njava Program --count=<int>");
            System.exit(-1);
        }
        try {
            cycleCount = Integer.parseInt(args[0].substring(8));
        } catch (Exception e) {
            System.out.println("Please, enter valid arguments.");
            System.exit(-1);
        }
        if (cycleCount < 1) {
            System.out.println("Please, enter valid arguments.");
            System.exit(-1);
        }

        Thread thread1 = new Thread(new Program(cycleCount, "Egg"));
        Thread thread2 = new Thread(new Program(cycleCount, "Hen"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Program(cycleCount, "Human").run();
    }

    private static boolean isCorrectString(String[] args) {
        if (args.length != 1) {
            return false;
        } else if (args[0] == null || args[0].isEmpty() || !args[0].startsWith("--count=")) {
            return false;
        }
        return true;
    }

    public Program(int count, String message) {
        this.count = count;
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++) {
            System.out.println(message);
        }

    }
}

