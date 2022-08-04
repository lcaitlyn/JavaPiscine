public class ProducerConsumer {
    private static boolean flag = false;

    public static synchronized void sayHen() throws InterruptedException {
        if (flag == false) {
            try {
                ProducerConsumer.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        flag = false;

        ProducerConsumer.class.notify();
    }

    public static synchronized void sayEgg() throws InterruptedException {
        if (flag) {
            try {
                ProducerConsumer.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        flag = true;

        ProducerConsumer.class.notify();
    }
}