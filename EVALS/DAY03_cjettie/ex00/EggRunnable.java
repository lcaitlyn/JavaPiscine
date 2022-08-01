public class EggRunnable implements Runnable {
    private int counter;
    private EggRunnable() {}
    public EggRunnable(int count, Object key) {
        this.counter = count;
    }
    @Override
    public void run() {
        for (; this.counter > 0; --counter) {
            System.out.println("Egg");
        }
    }
}
