import java.util.LinkedList;

public class MyRunnable implements Runnable {
    final LinkedList<String> urlQueue;
    static int index = 1;

    public MyRunnable(LinkedList<String> urlQueue) {
        this.urlQueue = urlQueue;
    }

    @Override
    public void run() {
        while (!urlQueue.isEmpty()) {
            String url = urlQueue.poll();

            if (url == null)
                return;

            int id = index++;
            System.out.println(Thread.currentThread().getName() + " start download file " + id);

            Downloader.downloader(url);

            System.out.println(Thread.currentThread().getName() + " finish download file " + id);
        }
    }
}
