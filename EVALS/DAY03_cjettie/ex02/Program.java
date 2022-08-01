import java.util.Random;

public class Program {
    private static final int MAX_MODULO = 1000;
    private static Long finalResult = 0L;
    public static void main(String [] args) {
        Integer arraySize = null;
        Integer threadsCount = null;
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }
            if ((arraySize = readIntegerOption(args[0], "--arraySize")) == null
                    || (threadsCount = readIntegerOption(args[1], "--threadsCount")) == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException numberFormatException) {
            System.out.println("Illegal arguments");
            System.exit(-1);
        }
        finalResult = 0L;
        short [] array = generateArray(arraySize);
        for (int noThreadSumCounter = 0; noThreadSumCounter < array.length; ++noThreadSumCounter) {
            finalResult += array[noThreadSumCounter];
        }
        System.out.println("Sum: " + finalResult);
        Mathematician [] mathematicians = Mathematician.getMathematicians(array, threadsCount);
        Thread [] threads = new Thread[mathematicians.length];
        for (int counter = 0; counter < mathematicians.length; ++counter) {
            threads [counter] = new Thread(mathematicians[counter]);
        }
        for (int counter = 0; counter < mathematicians.length; ++counter) {
            threads [counter].start();
        }
        try {
            for (int counter = 0; counter < mathematicians.length; ++counter) {
                threads[counter].join();
            }
        } catch (InterruptedException interruptedException) {
            System.out.println("Something goes wrong with threads");
        }
        finalResult = 0L;
        for (int counter = 0; counter < mathematicians.length; ++counter) {
            finalResult += mathematicians[counter].getResult();
        }
        System.out.println("Sum by threads: " + finalResult);
        System.exit(0);
    }

    private static short [] generateArray(int size) {
        short [] array = new short[size];
        while (size > 0) {
            array[--size] = (short)(-MAX_MODULO + (Math.random() * (2 * MAX_MODULO)));
        }
        return array;
    }
    private static Integer readIntegerOption(String fullOption, String optionOnly) {
        if (fullOption.length() < optionOnly.length() + 2) {
            return null;
        }
        if (fullOption.indexOf(optionOnly) != 0) {
            return null;
        }
        return Integer.parseInt(fullOption.replaceFirst(optionOnly + "=", ""));
    }
}
