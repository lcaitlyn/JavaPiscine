package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Links
{
    ArrayList<String> urls;
    Links(Path absPath)
    {
        urls = new ArrayList<>();
        checkPath(absPath);
    }
    void checkPath(Path absPath)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(absPath.toString()));
            String line;

            while ((line = reader.readLine()) != null)
            {
                if (line.length() > 0)
                {
                    this.urls.add(line);
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error: File not found.");
            System.exit(-1);
        }
    }
    ArrayList<String> getContainer()
    {
        return this.urls;
    }
    String getUrl(int i) throws ArrayIndexOutOfBoundsException
    {
        try
        {
            return this.urls.get(i);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Error: index of the array is out of bounds.");
            System.exit(-1);
        }
        return this.urls.get(i);
    }

    int getSize()
    {
        return this.urls.size();
    }
}
