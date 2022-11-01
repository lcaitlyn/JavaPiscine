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

    public static double mySqrt(int x) {
        double flag = 0.1d;
        
        double val = x;
        
        double last;
        
        if (x <= 0) {
            return 0;
        }
        
        do {
            last = val;
            val = (val + x / val) / 2;
        } while (val - last > flag || val - last < -flag);
        
        return  val;
    }
    
    public static int myCeil(double x) {
        if (x - (int)x > 0)
            return (int)x + 1;
        return (int)x;
    }
    
    public static boolean isPrime(int x) {
        int root = myCeil(mySqrt(x));

        int i = 2;

        int iter = 1;
        

        while (i <= root && x != 2 && x != 3) {
            if (x % i == 0) {
                return (false);
            }
            else {
                iter++;
                i++;
            }
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

        System.out.println("Count of coffee-request - " + i);

        sc.close();
    }
}