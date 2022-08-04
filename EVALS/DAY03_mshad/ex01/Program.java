public class Program {
    private static int cycleCount = 0;

    public static void main(String[] args) throws InterruptedException {
        if (!isCorrectString(args)) {
            System.out.println("Please, enter valid arguments:\njava Program --count=<unsigned int>");
            System.exit(-1);
        }
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread hen = new Thread(new Hen(cycleCount, producerConsumer));
        Thread egg = new Thread(new Egg(cycleCount, producerConsumer));

        hen.start();
        egg.start();

    }

    private static boolean isCorrectString(String[] args) {
        if (args.length != 1) {
            return false;
        } else if (args[0] == null || args[0].isEmpty() || !args[0].startsWith("--count=")) {
            return false;
        }
        try {
            cycleCount = Integer.parseInt(args[0].substring(8));
        } catch (Exception e) {
            return false;
        }
        if (cycleCount < 1) {
            return false;
        }
        return true;
    }
}
