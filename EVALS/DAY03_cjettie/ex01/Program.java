public class Program {
    static Object key = new Object();

    public static void main(String[] args) {
        if (args.length == 1) {
            String [] argsSplited = args[0].split("=");
            if (argsSplited.length == 2
                && argsSplited[0].equals("--count")) {
                try {
                    int count = Integer.parseInt(args[0].replaceFirst("--count=", ""));
                    EggRunnable eggRunnable = new EggRunnable(count, key);
                    HenRunnable henRunnable = new HenRunnable(count, key);
                    Thread eggThread = new Thread(eggRunnable);
                    Thread henThread = new Thread(henRunnable);
                    henThread.start();
                    eggThread.start();
                    try {
                        eggThread.join();
                        henThread.join();
                    } catch (InterruptedException interruptedException) {
                        System.out.println("Something interrupt tread");
                    }
                    System.out.println("Human");
                    System.exit(0);
                }
                catch (NumberFormatException numberFormatException) {
                    System.out.println("Not a integer");
                }
            } else {
                System.out.println("Wrong argument format");
            }
        } else {
            System.out.println("Wrong argument amount");
        }
        System.exit(-1);
    }
}