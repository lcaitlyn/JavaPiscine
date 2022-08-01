import java.util.List;
import java.util.Map;

public class Compare {
    
    private final String PROCESSED = "PROCESSED";
    private final String UNDEFINED = "UNDEFINED";
    private Map<List<Integer>, String> signaturesList;

    public Compare(Map<List<Integer>, String> signaturesList) {
        this.signaturesList = signaturesList;
    }

    public byte[] compareSignature(int[] fileBytes)
    {
        boolean equal = false;

        for (Map.Entry<List<Integer>, String> it : signaturesList.entrySet()){
            List<Integer> signBytes = it.getKey();
            for (int i = 0; i < signBytes.size(); i++) {
                if (signBytes.get(i) == fileBytes[i]) {
                    equal = true;
                } else {
                    equal = false;
                }                
            }
            if (equal == true) {
                byte[] buffer = (it.getValue()).getBytes();
                System.out.println(PROCESSED);
                return buffer;
            }
        }
        System.out.println(UNDEFINED);
        return null;
    }
}
