import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import java.util.Iterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dictionary {

    private BufferedReader buffReader;
    private Map<String, Integer> dictMap;
    private String dictFile = "dictionary.txt";

    public Dictionary(String[] args) throws IOException {
        
        
        buffReader = new BufferedReader(new FileReader(args[0]));
        dictMap = new TreeMap<String, Integer>();
        String line = buffReader.readLine();
                
        while(line != null) {
            addNewToDictionary(line);
            line = buffReader.readLine();
        }
        buffReader.close();
        buffReader = new BufferedReader(new FileReader(args[1]));
        line = buffReader.readLine();
        while(line != null) {
            addNewToDictionary(line);
            line = buffReader.readLine();
        }
        createDictFile();
    }

    private void createDictFile() throws IOException
    {
        BufferedWriter dictBuffWriter = new BufferedWriter(new FileWriter(dictFile));
        Iterator <Map.Entry<String, Integer>> it = dictMap.entrySet().iterator();
        Map.Entry<String, Integer> entry;

        if (it.hasNext()) {
            entry = it.next();
            dictBuffWriter.write(entry.getKey());
        }
        while (it.hasNext()) {
            entry = it.next();
            dictBuffWriter.write(", " + entry.getKey());
        }
        dictBuffWriter.close();
    }

    private void addNewToDictionary(String line){

            String[] strArr = line.split(" ");
            for (String s : strArr) {
                if (this.dictMap.get(s) == null) {
                    dictMap.put(s, 1);
                }
            }
    }
    
    public Map<String, Integer> getDictionary() {
        return this.dictMap;
    }
}
