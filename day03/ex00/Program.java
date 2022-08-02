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

        MyThreadEgg egg = new MyThreadEgg(size);
        Thread hen = new Thread(new MyRunnableHen(size));

        egg.start();
        hen.start();


        try {
            hen.join();
            egg.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < size; i++) {
            System.out.println("Human");
        }
    }
}