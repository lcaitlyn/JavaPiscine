public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Добавьте агрумент --count=SIZE");
            System.exit(-1);
        }

        int size = Integer.parseInt(args[0].replaceFirst("--count=", ""));

        if (size < 1) {
            System.err.println("Invalid Size");
            System.exit(-1);
        }

        Object key = new Object();

        Hen hen = new Hen(key, size);
        Egg egg = new Egg(key, size);

        try {
            hen.start();
            egg.start();
            hen.join();
            egg.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}