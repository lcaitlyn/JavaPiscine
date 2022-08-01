public class Mathematician implements Runnable {
    private static int identifierCounter = 0;
    private int identifier;

    private Long result = 0L;
    private short [] array;
    private int firstIndex;
    private int lastIndex;

    {
        this.identifier = identifierCounter++;
    }

    private Mathematician() {}
    private Mathematician(short [] array, int firstIndex, int lastIndex) {
        this.array = array;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    public static Mathematician [] getMathematicians(short [] array, int mathematicianAmount) {
        if (array.length < mathematicianAmount) {
            mathematicianAmount = array.length;
        }
        Mathematician [] result = new Mathematician[mathematicianAmount];
        int firstIndex = 0;
        int lastIndex;
        for (int mathematicianCounter = 0; mathematicianCounter < mathematicianAmount; ++mathematicianCounter) {
            if (mathematicianCounter < mathematicianAmount - 1) {
                lastIndex = array.length / mathematicianAmount * (mathematicianCounter + 1);
            } else {
                lastIndex = array.length;
            }
            result[mathematicianCounter] = new Mathematician(array, firstIndex, lastIndex);
            firstIndex = lastIndex;
        }
        return result;
    }

    @Override
    public void run() {
        for (int counter = firstIndex; counter < lastIndex; ++counter) {
            result += array[counter];
        }
        System.out.println("Thread " + identifier + ": from " + firstIndex + " to " + lastIndex + " sum is " + result);
    }

    public Long getResult() {
        return result;
    }
}
