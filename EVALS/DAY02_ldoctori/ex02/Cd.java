public class Cd {

    private CurrentFolder currentFolder;

    public Cd(CurrentFolder currentFolder){
        this.currentFolder = currentFolder;
    }

    public void doCd(String folder) throws SomeException {

        try {
            currentFolder.setCurrentFolder(folder);
        } catch (Exception e) {
            System.out.println("No such folder");
        }
    }
    
}
