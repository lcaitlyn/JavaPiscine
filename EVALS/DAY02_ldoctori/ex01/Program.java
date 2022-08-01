import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Program {
    
    public static void main(String[] args) throws IOException {
        
        Dictionary dict;
        try {
            dict = new Dictionary(args);   
        } catch (Exception e) {
            System.out.println("Command rule: java Program file1 file2. Try again.");
            return ;
        }
        Map<String, Integer> dictMap = dict.getDictionary();
        TextVector textVector = new TextVector(dictMap);
        Cosine cosA = new Cosine();
        Map<String, Integer> tV1, tV2;

        tV1 = textVector.makeTextVector(args[0]);
        tV2 = textVector.makeTextVector(args[1]);   
        cosA.cosCalculate(tV1, tV2);
        System.out.println("Similarity = " + limitPrecision(cosA.getCoine(), 2));
    }

        public static double limitPrecision(double cosA, int maxDigitsAfterDecimal) {
        int multiplier = (int) Math.pow(10, maxDigitsAfterDecimal);
        double truncated = (double) ((long) (cosA * multiplier)) / multiplier;
        return truncated;
    }

}
