import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadWorker implements Runnable {
    private static int identifierCounter = 0;
    private int identifier;
    private DownloadManager downloadManager;
    {
        identifier = ++identifierCounter;
    }

    private DownloadWorker() {}
    public DownloadWorker(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    @Override
    public void run() {
        DownloadFile downloadFile;
        InputStream inputStream;
        while (true) {
            synchronized (downloadManager) {
                downloadFile = downloadManager.getNextDownloadFile();
                if (downloadFile == null) {
                    return;
                }
            }
            try {
                inputStream = downloadFile.url.openStream();
            }
            catch (IOException ioException) {
                System.err.println("At thread " + identifier + ": " + "Unknown error during input from file");
                break;
            }
            try {
                Path path = Paths.get(downloadFile.url.getFile()).getFileName();
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Thread-" + identifier + " finish download file number " + downloadFile.filePosition);
            }
            catch (java.io.IOException ioException) {
                System.err.println("At thread " + identifier + ": " + "Unknown error during downloading file");
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                System.err.println("At thread " + identifier + ": " + "Error during file closing");
                break;
            }

        }
    }
}
