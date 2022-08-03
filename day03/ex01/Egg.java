public class Egg extends Thread {
    private final int size;
    private final Object key;

    public Egg(Object key, int size) {
        this.size = size;
        this.key = key;
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < size; i++) {
            synchronized (key) {
                System.out.println("Egg");
                try {
                    key.notify();
                    key.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}