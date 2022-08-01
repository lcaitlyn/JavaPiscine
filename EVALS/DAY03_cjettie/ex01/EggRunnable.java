public class EggRunnable implements Runnable {
    private int counter;
    private Object key;

    private EggRunnable() {
    }

    public EggRunnable(int count, Object key) {
        this.counter = count;
        this.key = key;
    }

    @Override
    public void run() {
        for (; this.counter > 0; --counter) {
            System.out.println("Egg");
            try {
                synchronized (key) {
                    key.notify();
                    key.wait();
                }
            } catch (InterruptedException interruptedException) {
                System.exit(-1);
            }
        }
    }
}
