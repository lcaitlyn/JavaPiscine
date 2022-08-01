import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class Program{


    public static void main (String[] args) throws IOException{

        CurrentFolder currentFolder = new CurrentFolder(args[0]);
        Scanner sc = new Scanner(System.in);
        String str;
        String[] parsedStr;
        Ls ls = new Ls(currentFolder);
        Cd cd = new Cd(currentFolder);
        Mv mv = new Mv(currentFolder);
        
        str = sc.nextLine();
        while (str.equals("exit") == false) {
            if (str.equals("ls") == true) {
                ls.doLs();
            } else {
                parsedStr = str.split(" ");
                if (parsedStr[0].equals("cd") == true) {
                    cd.doCd(parsedStr[1]);
                } else if (parsedStr[0].equals("mv") == true) {
                    mv.doMv(parsedStr);
                }
            }
            str = sc.nextLine();
        }
    }
}