import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {

    public static String createNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    public static void downloader(String url) {
        if (url == null)
            return;

        try (InputStream inputStream = URI.create(url).toURL().openStream()) {
            Files.copy(inputStream, Paths.get(createNameFromUrl(url)), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
