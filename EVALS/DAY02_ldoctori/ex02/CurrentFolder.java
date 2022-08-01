import java.io.File;
import java.io.IOException;

public class CurrentFolder {
    
    private File folder;
    private String flag = "--current-folder";

    public CurrentFolder(String arg) throws IOException{
        
        String[] parsedArg = arg.split("=");
        if (parsedArg[0].equals(flag)) {
            this.folder = new File(parsedArg[1]);
            checkIsDir(folder);          
        } else {
            throw new IOException("Wrong flag!");
        }
        System.out.println(folder.getCanonicalPath());
    }

    public void setCurrentFolder(String folder) throws IOException {

        File newFolder = new File(this.folder.getCanonicalPath() + "/" + folder);
        checkIsDir(newFolder);
        this.folder = newFolder;
        System.out.println(this.folder.getCanonicalPath());
    }

    private void checkIsDir(File chekFile) throws SomeException {
        if (chekFile.isDirectory() == true)
            return ;
        throw new SomeException();
    }

    public File getCurrentFolder() {
        return this.folder;
    }

    

}
