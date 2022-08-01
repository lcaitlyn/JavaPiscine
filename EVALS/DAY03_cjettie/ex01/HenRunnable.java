public class HenRunnable implements Runnable {
    private int counter;
    private Object key;

    private HenRunnable() {
    }

    public HenRunnable(int count, Object key) {
        this.counter = count;
        this.key = key;
    }

    @Override
    public void run()     {
        for (; this.counter > 0; --counter) {
            synchronized (key) {
                try {
                    key.wait();

                } catch (InterruptedException interruptedException) {
                    System.exit(-1);
                }
                System.out.println("Hen");
                key.notify();
            }
        }
    }
}
