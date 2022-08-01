import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class SignatureFile {

    private Map<List<Integer>, String> signaturesMap;
    private final String path = "signatures.txt";
    private final String hex = "0123456789ABCDEF";

    public SignatureFile() throws IOException {
        
        signaturesMap = new HashMap<List<Integer>, String>();
        parseSignatureFile();
    }

    private void parseSignatureFile() throws IOException {

        FileInputStream fileinStream = new FileInputStream(this.path);
        int c = 0;
        String str;
        while (c != -1) {
            str = "";
            c = fileinStream.read();
            do { 
                if (c == '\n')
                    break; 
                str += (char) c + "";
                c = fileinStream.read();
            } while (c != -1);
            addNewPare(str);
        }
        fileinStream.close();
    }

    private void addNewPare(String str){
        
        int i = 0;
        String sign = "";
        char[] strArr = str.toCharArray();
        List<Integer> signBytes = new ArrayList<Integer>();
        String oneByte;
        while (strArr[i] != ','){
            sign += strArr[i] + "";
            i++; 
        }
        i += 2;
        while (i < strArr.length)
        {
            oneByte = "";
            while ((i < strArr.length) && (strArr[i] != ' '))
            {
                oneByte += strArr[i] + "";
                i++;
            }
            signBytes.add(Integer.parseInt(oneByte, 16));
            i++;
        }
        signaturesMap.put(signBytes, sign);
    }

    public Map<List<Integer>, String> getSignaturesMap(){
        return this.signaturesMap;
    }
}
