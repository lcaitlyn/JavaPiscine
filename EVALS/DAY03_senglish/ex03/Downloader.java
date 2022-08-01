package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

public class Downloader implements Runnable {
    private final String link;
    private final int index;

    Downloader(String url, int taskIndex)
    {
        this.link = url;
        this.index = taskIndex;
    }

    @Override
    public void run() {
        String threadNum = Thread.currentThread().getName();
        String id = threadNum.substring(threadNum.lastIndexOf('-') + 1);

        try {
            System.out.println("Thread #" + id + " is starting to download file number #" + index);
            URL url = new URL(link);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(Paths.get(link).getFileName().toString());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.flush();
            fos.close();
            rbc.close();
            System.out.println("Thread #" + id + " is finished downloading file number #" + index);
        } catch (IOException e) {
            System.out.println("Error: Couldn't download from URL: " + link);
        }
    }
}