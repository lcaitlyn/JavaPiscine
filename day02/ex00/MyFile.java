import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFile {

    public static String getData(String fileName, int length) {
        if (!new File(fileName).isFile() || !new File(fileName).canRead())
            return null;

        File newFile = new File(fileName);

        FileInputStream fileInputStream = null;

        String res = "";

        int i = 0;

        length = (length == 0) ? (int)newFile.length() : length;

        int [] allText = new int[length];

        try {
            fileInputStream = new FileInputStream(newFile);

            try {
                while (i < length && (allText[i] = fileInputStream.read()) != -1) {
                    res  += (char)allText[i];
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

}
