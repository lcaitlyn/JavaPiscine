import java.io.*;
import java.util.*;


public class Program {
    public static void writeDictionary(Collection<String> collection) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("dictionary.txt", false);
            for (Object s: collection) {
                fileWriter.write(s + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static double denominator(int [] A, int [] B) {
        int resA = 0;
        int resB = 0;

        for (int i = 0; i < A.length; i++) {
            resA += A[i] * A[i];
            resB += B[i] * B[i];
        }

        return Math.sqrt(resA) * Math.sqrt(resB);
    }

    public static int numerator(int [] A, int [] B) {
        int res = 0;

        for (int i = 0; i < A.length; i++) {
            res += A[i] * B[i];
        }

        return res;
    }
    public static int [] getVector(File newFile, Collection<String> collection) {
        ArrayList arrayList = new ArrayList();

        int [] vector = new int[collection.size()];

        arrayList.addAll(collection);
        try {
            FileReader fileReader = new FileReader(newFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String newLine = new String();

            newLine = bufferedReader.readLine();
            while (newLine != null) {
                for (Object s: newLine.split(" ")) {
                    if (arrayList.contains(s)) {
                        vector[arrayList.indexOf(s)]++;
                    }
                }
                try {
                    newLine = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fileReader.close();
            bufferedReader.close();

            return vector;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Collection<String> addDictionaryFromFile(File newFile, Collection<String> collection) {
        try {
            FileReader fileReader = new FileReader(newFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String newLine = new String();

            newLine = bufferedReader.readLine();
            while (newLine != null) {
                collection.addAll(Arrays.asList(newLine.split(" ")));
                try {
                    newLine = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fileReader.close();
            bufferedReader.close();

            return collection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Добавьте файлы в аргументы");
            System.exit(-1);
        }

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        Collection<String> collection = new TreeSet<>(Comparator.naturalOrder());

        if (!file1.canRead() || !file2.canRead()) {
            System.err.println("Файлы не читабельны");
            System.exit(-1);
        }

        collection = addDictionaryFromFile(file1, collection);
        collection = addDictionaryFromFile(file2, collection);

        int [] A = getVector(file1, collection);
        int [] B = getVector(file2, collection);

        System.out.printf("Similarity = %.3f\n", numerator(A, B) / denominator(A, B));
        writeDictionary(collection);
    }
}