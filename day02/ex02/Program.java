import java.io.*;

public class Program {

    public static void main(String[] args) {
	    if (args.length != 1) {
            System.err.println("Error! Input --current-folder=DIR");
            System.exit(-1);
        }

        File dir = new File(args[0].replaceFirst("--current-folder=", ""));

        if (!dir.isDirectory()) {
            System.err.println("Error! Input real directory");
            System.exit(-1);
        }

        try {
            Microshell microshell = new Microshell(dir.getCanonicalPath());
            microshell.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
