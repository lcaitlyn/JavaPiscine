public class HenRunnable implements Runnable {
    private int counter;
    private HenRunnable() {}
    public HenRunnable(int count, Object key) {
        this.counter = count;
    }
    @Override
    public void run() {
        for (; this.counter > 0; --counter) {
            System.out.println("Hen");
        }
    }
}
