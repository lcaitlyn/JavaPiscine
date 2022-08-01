import java.util.Scanner;
import java.util.Map.Entry;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Program {

    private final static String outFile = "result.txt";
    public static void main(String[] args) throws IOException {
        
        SomeFile someFile = new SomeFile();
        Scanner scanner = new Scanner(System.in);
        String path;
        SignatureFile signFile = new SignatureFile();
        Compare doCompare = new Compare(signFile.getSignaturesMap());
        byte[] answer;
        boolean start = true;
        FileOutputStream outFileStream = new FileOutputStream(outFile);

        path = scanner.nextLine();
        if (path.equals("42") == false){
            answer = doCompare.compareSignature(someFile.readFile(path));
            if (answer != null){
                start = false;
                outFileStream.write(answer);
            }
            path = scanner.nextLine();
        }
        while (path.equals("42") == false) {
            answer = doCompare.compareSignature(someFile.readFile(path));
            if (answer != null) {
                if (start == false) {
                outFileStream.write("\n".getBytes());
                }
                outFileStream.write(answer);
                start = false;
            }
            path = scanner.nextLine();
        }
        outFileStream.close();
    }
 }