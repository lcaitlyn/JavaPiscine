import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mv {
    
    private CurrentFolder currentFolder;

    public Mv(CurrentFolder currentFolder) {
        this.currentFolder = currentFolder;
    }

    public void doMv(String[] args) throws IOException {

        File file = new File(currentFolder.getCurrentFolder().getCanonicalFile() + "/" + args[1]);
        try {
            checkFileOrDirExist(file);
            checkFile(file);
        } catch (Exception e) {
            printException();
        }

        File whereFile = new File(currentFolder.getCurrentFolder().getCanonicalFile() + "/" + args[2]);
        
        if (whereFile.isDirectory() == false) {
            file.renameTo(whereFile);
        } else {
            Files.move(file.toPath(), Paths.get(whereFile.getCanonicalPath() + "/" + file.getName()));
        }


    }

    private void checkFile(File file) throws SomeException {
        if (file.isFile() == true) {
            return ;
        }
        throw new SomeException();
    }

    private void checkFileOrDirExist(File file) throws SomeException {

        if (file.exists() == true) {
            return ;
        }
        throw new SomeException();
    }

    private void printException() {

        System.out.println("Error: no such file. Command rule: mv arg1 arg2");
        System.out.println("arg1: some file");
        System.out.println("arg2: some new file name or new file location");
    }

}
