import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SignatureList signatureList = new SignatureList();

        File outputFile = new File("result.txt");

        outputFile.delete();
        signatureList.createSignatures("/Users/lcaitlyn/Desktop/signatures.txt");
        String fileName = scanner.nextLine().trim().replaceAll("\"", "");

        while (!fileName.equals("42")) {
            if (fileName.length() == 0) {
                fileName = scanner.nextLine().trim().replaceAll("\"", "");
                continue;
            }

            String type = signatureList.checkSignature(signatureList.convertToSignature(MyFile.getData(fileName, 4).toCharArray()));

            if (type != null) {
                System.out.println("PROCESSED ");
                if (!outputFile.exists()) {
                    try {
                        outputFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile, true);

                    try {
                        fileOutputStream.write(type.getBytes(StandardCharsets.UTF_8));
                        fileOutputStream.write('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            fileName = scanner.nextLine().replaceAll("\"", "").replaceAll(" ", "");
        }
    }
}
