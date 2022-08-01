package ex02;

import java.util.stream.IntStream;

public class Program
{
    public static void main(String[] args) {
        parseArgs(args);
        int arrayNum = findNum(args[0], 12);
        int threadNum = findNum(args[1], 15);
        parseNum(arrayNum, threadNum);
        int[] array = fillArray(arrayNum);
        System.out.println("Sum of Array elements: " + IntStream.of(array).sum());
        Counter counter = new Counter("0");
        runThreadArray(counter, arrayNum, threadNum, array);
        System.out.println("Threads Sum of Array elements: " + counter.getSum());
    }
    static void parseArgs(String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Program should contain two arguments.");
            System.exit(-1);
        }
        if (!args[0].startsWith("--arraySize="))
        {
            System.err.println("The first argument of program should be starting from --arraySize= and number");
            System.exit(-1);
        }
        if (!args[1].startsWith("--threadsCount="))
        {
            System.err.println("The second argument of program should be starting from --threadsCount= and number");
            System.exit(-1);
        }
    }
    static int findNum(String arg, int amount)
    {
        int i = 0;
        try
        {
            i = Integer.parseInt(arg.substring(amount));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return i;
    }
    static void parseNum(int arrayNum, int threadNum)
    {
        if (threadNum <= 0 || arrayNum <= 0)
        {
            System.err.println("Program should contain at least one element in array and at least one thread.");
            System.exit(-1);
        }
        if (threadNum > 2000000)
        {
            System.err.println("Given number of threads is too big.");
            System.exit(-1);
        }
    }
    static int[] fillArray(int arrayNum)
    {
        int[] array = new int[arrayNum];
        for (int count = 0; count < arrayNum; ++count)
        {
            array[count] = count;
            if (count % 2 == 0)
                array[count] = -(int) (0 + Math.random() * 1001);
            else
                array[count] = (int) (0 + Math.random() * 1001);
        }
        return array;
    }
    static void runThreadArray(Counter counter, int arrayNum, int threadNum, int[] array)
    {
        int divArray = arrayNum / threadNum;
        Thread[] thread = new Thread[threadNum];
        for (int count = 0; count < threadNum - 1; ++count)
        {
            int[] newArray = new int[divArray];
            for (int fill = 0; fill < divArray; ++fill)
            {
                newArray[fill] = array[count * divArray + fill];
            }
            thread[count] = new Thread(createRunnable(newArray, counter), String.valueOf(count));
        }
        int remArray = arrayNum % threadNum + divArray;
        int[] newArray = new int[divArray + arrayNum % threadNum];
        for (int fill = 0; fill < remArray; ++fill)
        {
            newArray[fill] = array[(threadNum - 1) * divArray + fill];
        }
        String name = String.valueOf(threadNum - 1);
        thread[threadNum - 1] = new Thread(createRunnable(newArray, counter), name);
        for (int count = 0; count < threadNum; ++count)
        {
            thread[count].start();
        }
        try
        {
            for (int count = 0; count < threadNum; ++count)
            {
                thread[count].join();
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Thread can not be joined.");
            System.exit(-1);
        }
    }
    static synchronized Runnable createRunnable(int[] array, Counter counter)
    {
        return () -> counter.count(array);
    }
}
