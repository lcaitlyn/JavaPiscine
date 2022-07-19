import java.util.Scanner;

public class Program {

    private static long myPow(long base, long power) {
        long result = 1;
        
        while (power > 0) {
            result *= base;
            --power;
        }
        
        return result;
    }

    public static int getGrade(long grades, int weakCounter) {
        return (int)(weakCounter != 0 ? grades / myPow(10, weakCounter) : grades % 10);
    }

    public static void printGraph(int x) {
        while (x-- > 0)
            System.out.print("=");
        
        System.out.println(">");
    }

    public static long addGrade(long grades, int x) {
        return grades * 10 + x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long grades = 0;

        int weakCounter = 0;

        int min = 9;

        int x = 0;

        String str = "";

        while (weakCounter < 18) {
            str = sc.nextLine();

            if (str.equals("42"))
                break;
            else if (!str.equals("Week " + (weakCounter + 1))) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            min = 9;

            for (int i = 0; i < 5; i++) {
                if (!sc.hasNextInt())
                {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }

                x = sc.nextInt();

                if (x < 1 || x > 9) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                else if (x < min) {
                    min = x;
                }
            }
            grades = addGrade(grades, min);
            weakCounter++;
            sc.nextLine();
        }
        
        x = 1;

        while (weakCounter > 0) {
            System.out.print("Week " + x + " ");
            printGraph(getGrade(grades, weakCounter - 1));
            grades %= myPow(10, weakCounter - 1);
            weakCounter--;
            x++;
        }
    }
}
