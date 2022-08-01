import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SomeFile {

    public SomeFile() {}

    public int[] readFile(String path) throws IOException
    {
        FileInputStream fileInStream = new FileInputStream(path);
        int[] fileBytes = new int[fileInStream.available()];

        for (int i = 0; i < fileInStream.available(); i++) {
            fileBytes[i] = fileInStream.read();
        }
        fileInStream.close();
        return fileBytes;
    }
    
}
