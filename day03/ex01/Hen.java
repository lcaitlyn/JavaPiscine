public class Hen extends Thread {
    private final int size;
    private final Object key;

    public Hen(Object key, int size) {
        this.size = size;
        this.key = key;
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < size; i++) {
            synchronized (key) {
                try {
                    key.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hen");
                key.notify();
            }
        }
    }
}
