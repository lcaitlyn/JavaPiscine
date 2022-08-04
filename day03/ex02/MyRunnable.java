import java.util.concurrent.BlockingQueue;

public class MyRunnable implements Runnable {
    BlockingQueue<Integer> queue;
    final int [] arr;
    final int start;
    final int finish;
    final int id;

    public MyRunnable(BlockingQueue<Integer> queue, int[] arr, int start, int finish) {
        this.queue = queue;
        this.arr = arr;
        this.start = start;
        this.finish = finish;
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    private int getSum(int [] arr, int start, int finish) {
        int sum = 0;

        for (int i = start; i < finish; i++) {
            sum += arr[i];
        }

        System.out.printf("Thread %s: from %d to %d sum is %d\n", id, start, finish, sum);
        return sum;
    }

    @Override
    public void run() {
        try {
            queue.put(getSum(arr, start, finish));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
