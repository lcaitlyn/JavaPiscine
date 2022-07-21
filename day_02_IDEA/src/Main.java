import java.io.*;
import java.util.Arrays;

public class Main {

    public static void signature() {

        char [] charArray = new char [1000];

        int [] inHex = new int[2];

        String str = "89504E470D0A1A0A";

        charArray = str.toCharArray();

        for (char currentChar : charArray) {
            while ((currentChar >= 'A' && currentChar <= 'F')
                    || (currentChar >= '0' && currentChar <= '9')) {
                if (currentChar >= 'A') {
                    currentChar = (char) (currentChar -'A' + 10);
                } else {
                    currentChar -= '0';
                }
            }
        }
    }

    public static void main(String[] args) {

        SignatureList signatureList = new SignatureList();

        signatureList.createSignatures("/Users/lcaitlyn/Desktop/signatures.txt");
        signatureList.printSignatures();

        System.exit(1);









        char [] firstBytes = new char[8];

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/Users/lcaitlyn/Desktop/123.png");
            for (int i = 0; i < 8; i++) {
                try {
                    firstBytes[i] = (char)fileInputStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String b = "";

        for (int i = 0; i < firstBytes.length; i++) {
            System.out.print((int)firstBytes[i] + " ");
            b += (int)firstBytes[i];
        }
        System.out.println();
        System.out.println(b);




        String str = "89 50 4E 47 0D 0A 1A 0A";
        if (str.indexOf(',') > 0) {
            str = str.substring(str.indexOf(',') + 2, str.length());
        }
        System.out.println(str);

        String [] strArray = str.split(" ");

        System.out.println(strArray[0] + strArray[1] + strArray[2]);

        System.out.println();

        for (String str1 : strArray) {
            System.out.print(Integer.parseInt(str1, 16));
        }

//        SignatureList signatureList = new SignatureList();
//
//        signatureList.addSignature("71737056");
//        signatureList.addSignature("13780787113102610");
//
//        System.out.println();
//
//        System.out.println(signatureList.checkSignature(b));

    }
}
