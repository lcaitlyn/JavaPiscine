import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Ls {

    private CurrentFolder currentFolder;

    public Ls(CurrentFolder currentFolder) {

        this.currentFolder = currentFolder;
    }


    public void doLs() throws IOException{

        String[] fileList;
        long length = 0;
        
        if (currentFolder.getCurrentFolder().isDirectory() == true) {
            fileList = currentFolder.getCurrentFolder().list();
        } else {
            return ;
        }
        
        File someFile;
        for (String s : fileList) {
            someFile = new File(currentFolder.getCurrentFolder().getCanonicalPath() + "/" + s);
            if (someFile.isDirectory() == true) {
                length = countDirectorySize(someFile);
            } else {
                length = someFile.length();
            }
                System.out.println(someFile.getName() + " " + length / 1000 + " KB");
        }
    }

    private long countDirectorySize(File directory) throws IOException
    {   
        long length = 0;
        String[] filelist = directory.list();
        File someFile;

        for (String s : filelist) {
            someFile = new File(directory.getCanonicalPath() + "/" + s);
            if (someFile.isDirectory() == true) {
                length += countDirectorySize(someFile);
            } else {
                length += someFile.length();
            }
        }

        return length;
    }
    
}
