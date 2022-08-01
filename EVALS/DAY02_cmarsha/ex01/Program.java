import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Program {

    public static final int maxFileSize = 10485760;
    public static String outputFile = "dictionary.txt";
    public static ArrayList<String> dictA = new ArrayList<>();
    public static ArrayList<String> dictB = new ArrayList<>();
    public static HashMap<String, Integer> dictionary = new HashMap<>();

    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            File fileA = new File(args[0]);
            File fileB = new File(args[1]);
            if (fileA.length() > maxFileSize || fileB.length() > maxFileSize) {
                throw new Exception("The file is too large!");
            }

            BufferedReader reader0 = new BufferedReader(new FileReader(args[0]));
            BufferedReader reader1 = new BufferedReader(new FileReader(args[1]));
            BufferedWriter writer;
            addToDictionary(dictA, reader0);
            addToDictionary(dictB, reader1);

            FileWriter toFile = new FileWriter(outputFile);
            writer = new BufferedWriter(toFile);
            for (String word : dictionary.keySet()) {
                writer.write(word + " ");
            }
            reader0.close();
            reader1.close();
            writer.close();

            List<Integer> frequencyOccurrenceA = getFrequency(dictA);
            List<Integer> frequencyOccurrenceB = getFrequency(dictB);
            int numerator = getNumerator(frequencyOccurrenceA, frequencyOccurrenceB);
            double denominator = getDenominator(frequencyOccurrenceA, frequencyOccurrenceB);
            double similarity = (double)((int)((numerator / denominator) * 100)) / 100;
            System.out.println("Similarity = " + similarity);
        }
    }

    public static void addToDictionary(ArrayList<String> inputFile,  BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] array = line.split(" ");
            inputFile.addAll(Arrays.asList(array));
            for (String word : array) {
                Integer count = dictionary.get(word);
                if (count == null) {
                    dictionary.put(word, 1);
                } else {
                    dictionary.put(word, ++count);
                }
            }
        }
    }

    public static List<Integer> getFrequency(List<String> file) {
        List<Integer> tmp = new ArrayList<>(dictionary.size());
        int i = 0;
        int counter = 0;
        for (String element : dictionary.keySet()) {
            for (String fromFile : file) {
                if (element.equals(fromFile))
                    counter++;
            }
            tmp.add(i, counter);
            i++;
            counter = 0;
        }
        return tmp;
    }

    public static int getNumerator(List<Integer> a, List<Integer> b) {
        int tmp = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            tmp += a.get(i) * b.get(i);
        }
        return tmp;
    }

    public static double getDenominator(List<Integer> a, List<Integer> b) {
        double pow2A = 0;
        for (Integer x : a)
            pow2A += x * x;
        double pow2B = 0;
        for (Integer x : b)
            pow2B += x * x;
        return (Math.sqrt(pow2A) * Math.sqrt(pow2B) * 100 / 100);
    }
}
