import java.util.Scanner;

public class Program {

    public static int getSum(int x) {
        int sum = 0;

        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static boolean isPrime(int x) {
        int root = ((int) Math.ceil(Math.sqrt(x)));

        int i = 2;

        while (i <= root && x != 2) {
            if (x % i == 0)
                return (false);
            else
                i++;
        }

        return (true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        int i = 0;

        while (x != 42) {
            if (!(x > 1))
                System.exit(-1);


            if (isPrime(getSum(x)))
                i++;

            x = sc.nextInt();
        }

        System.out.println("Count of coffee - request - " + i);

        sc.close();
    }
}
