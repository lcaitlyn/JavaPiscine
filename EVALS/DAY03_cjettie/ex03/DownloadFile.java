import java.net.URL;

public class DownloadFile {
    public int filePosition;
    public URL url;
    private DownloadFile () {}
    public DownloadFile(int filePosition, URL url) {
        this.filePosition = filePosition;
        this.url = url;
    }
}
