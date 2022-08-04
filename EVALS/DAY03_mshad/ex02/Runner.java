public class Runner implements Runnable {
    private int index;
    private int count;
    private int sumOneThread;

    public Runner(int index, int count) {
        this.index = index;
        this.count = count;
    }

    @Override
    public void run() {
        sumOneThread = Program.countSumInInterval(index, count);
        Program.countSumByThreads(sumOneThread);

        String info = Thread.currentThread().getName() +
                ": from " + index +
                " to " + (index + count - 1) +
                " sum is " + sumOneThread;
        System.out.println(info);
    }
}