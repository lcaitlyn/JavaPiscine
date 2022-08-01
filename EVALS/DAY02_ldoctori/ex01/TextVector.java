import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextVector {
    
    private Map<String, Integer> dictMap;

    public TextVector(Map<String, Integer> dictMap) {
        this.dictMap = dictMap;
    }

    public Map<String, Integer> makeTextVector(String file) throws IOException {
        
        Map<String, Integer> textVector = makeEmptyTextVector();
        BufferedReader buffReader = new BufferedReader(new FileReader(file));
        String[] lineArr;
        String line = buffReader.readLine();

        while(line != null) {
            lineArr = line.split(" ");
            for (String s : lineArr) {
                textVector.put(s, (textVector.get(s) + 1));
            }            
            line = buffReader.readLine();
        }
        buffReader.close();
        return textVector;
    }

    private Map<String, Integer> makeEmptyTextVector(){

        Map<String, Integer> textVector = new TreeMap<String, Integer>();
        for(Map.Entry<String, Integer> it : dictMap.entrySet())
            textVector.put(it.getKey(), 0);
        return textVector;
    }
}
