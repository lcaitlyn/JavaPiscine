import java.io.*;
import java.net.URL;
import java.util.LinkedList;

public class DownloadManager {
    int threadsAmount;
    String filesUrlsPath;
    LinkedList<DownloadFile> downloadFiles;
    {
        downloadFiles = new LinkedList<DownloadFile>();
    }
    private DownloadManager() {}
    public DownloadManager(int threadsAmount, String filesUrlsPath) {
        this.threadsAmount = threadsAmount;
        this.filesUrlsPath = filesUrlsPath;
    }
    public void StartDownloads () {
        readURLFile(filesUrlsPath);
        if (downloadFiles.size() < threadsAmount) {
            threadsAmount = downloadFiles.size();
        }
        DownloadWorker[] downloadWorkers = new DownloadWorker[threadsAmount];
        for (int counter = 0; counter < downloadWorkers.length; ++counter) {
            downloadWorkers[counter] = new DownloadWorker(this);
        }
        Thread[] threads = new Thread[downloadWorkers.length];
        for (int counter = 0; counter < downloadWorkers.length; ++counter) {
            threads[counter] = new Thread(downloadWorkers[counter]);
        }
        for (int counter = 0; counter < downloadWorkers.length; ++counter) {
            threads[counter].start();
        }
        try {
            for (int counter = 0; counter < downloadWorkers.length; ++counter) {
                threads[counter].join();
            }
        } catch (InterruptedException interruptedException) {
            System.err.println(interruptedException.toString());
        }
    }

    DownloadFile getNextDownloadFile() {
        if (downloadFiles.size() == 0) {
            return null;
        }
        DownloadFile downloadFile = downloadFiles.get(0);
        downloadFiles.remove(0);
        return downloadFile;
    }
    private void readURLFile(String filesUrlsPath) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filesUrlsPath);
        }
        catch (java.io.FileNotFoundException fileNotFoundException) {
            System.err.println("Wrong file with urls name");
            throw new RuntimeException();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String newLine;
        try {
            while ((newLine = bufferedReader.readLine()) != null) {
                int separator = newLine.indexOf(" ");
                if (separator == -1 || newLine.length() <= separator + 1) {
                    System.err.println("Wrong data line in files urls file");
                }
                Integer position = Integer.parseInt(newLine.substring(0, separator));
                newLine = newLine.substring(separator + 1);
                downloadFiles.add(new DownloadFile(position, new URL(newLine)));
            }
        } catch (java.io.IOException ioException) {
            throw new RuntimeException();
        }
        try {
            bufferedReader.close();
        } catch (IOException ioException) {
            System.err.println("Error while file closing");
            throw new RuntimeException(ioException);
        }
    }
}
