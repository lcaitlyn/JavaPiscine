import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureList {
    private List<String> signatures = new ArrayList<String>();

    public void createSignatures(String fileName) {
        FileInputStream fileInputStream = null;

        String str = "";

        int [] allText = new int[1024];

        String res = "";

        try {
            fileInputStream = new FileInputStream(fileName);

            int i = 0;

            try {
                while ((allText[i] = fileInputStream.read()) != -1) {
                    str += (char)allText[i];
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String sub : str.split("\n")) {
                res = "";

                if (sub.indexOf(',') > 0) {
                    sub = sub.substring(sub.indexOf(',') + 2);
                }

                for (String s : sub.split(" ")) {
                    if (s.isEmpty() != false) {
                        res += Integer.parseInt(s, 16);
                    }
                }

                System.out.println(res);

                signatures.add(res);
            }



            Collections.addAll(signatures, str.split("\n"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addSignature(String newSignature) {
        signatures.add(newSignature);
    }

    public boolean checkSignature(String newSignature) {
        for (int i = 0; i < signatures.size(); i++) {
            if (newSignature.equals(signatures.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void printSignatures() {
        for (int i = 0; i < signatures.size(); i++) {
            System.out.println(signatures.get(i));
        }
    }
}
