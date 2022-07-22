import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureList {
    private List<String []> signatures = new ArrayList<String []>(2);

    private void parseData(String str) {
        for (String sub : str.split("\r\n")) {
            String [] strArray = new String[2];

            String res = "";

            strArray[0] = sub.substring(0, sub.indexOf(","));

            if (sub.indexOf(',') > 0) {
                sub = sub.substring(sub.indexOf(',') + 2);
            }

            for (String s : sub.split(" ")) {
                res += Integer.parseInt(s, 16);
            }

            strArray[1] = res;
            signatures.add(strArray);
        }
    }

    public void createSignatures(String fileName) {
        File newFile = new File(fileName);

        if (!newFile.canRead())
            return;

        parseData(MyFile.getData(fileName, 0));
    }

    public void addSignature(String [] newSignature) {
        signatures.add(newSignature);
    }

    public String checkSignature(String newSignature) {
        for (int i = 0; i < signatures.size(); i++) {
            if (newSignature.contains(signatures.get(i)[1]) || signatures.get(i)[1].contains(newSignature)) {
                return signatures.get(i)[0];
            }
        }
        return null;
    }

    public void printSignatures() {
        for (int i = 0; i < signatures.size(); i++) {
            System.out.println(signatures.get(i)[0] + " = " + signatures.get(i)[1]);
        }
    }

    public String convertToSignature(char [] data) {
        String res = "";

        for (int i = 0; i < data.length; i++) {
            res += (int)data[i];
        }
        return res;
    }


}
