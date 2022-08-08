import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class Reader {

    public static boolean isURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Collection<String> addUrlDictionaryFromFile(File newFile, Collection<String> collection) {
        try {
            FileReader fileReader = new FileReader(newFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String newLine;

            newLine = bufferedReader.readLine();
            while (newLine != null) {
                for (String s : newLine.split(" ")) {
                    if (isURL(s)) {
                        collection.add(s);
                    }
                }
                try {
                    newLine = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fileReader.close();
            bufferedReader.close();

            return collection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
