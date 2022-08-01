package ex01;

public class Program
{
    public static void main(String[] args) {
        parseArgs(args);
        int num = findNum(args[0]);
        Printer printer = new Printer("hen");
        Thread thread2 = new Thread(createRunnable(num, printer), "egg");
        Thread thread1 = new Thread(createRunnable(num, printer), "hen");
        thread1.start();
        thread2.start();
        try
        {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e)
        {
            System.err.println("Thread can not be joined.");
            System.exit(-1);
        }
    }
    static void parseArgs(String[] args)
    {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Program should contain one argument starting from --count= and number");
            System.exit(-1);
        }
    }
    static int findNum(String arg)
    {
        int i = 0;
        try
        {
            i = Integer.parseInt(arg.substring(8));
        }
        catch (NumberFormatException e) {
            System.err.println("Argument should contain a number after --count=");
            System.exit(-1);
        }
        return i;
    }
    static synchronized Runnable createRunnable(int num, Printer printer)
    {
        return () -> printer.printOut(num);
    }
}
