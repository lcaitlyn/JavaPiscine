public class MyRunnableHen implements Runnable {
    private int size;

    public MyRunnableHen(int size) {
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            System.out.println("Hen");
        }
    }
}
