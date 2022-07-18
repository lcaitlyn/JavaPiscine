import java.util.Scanner;

public class Program {

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
        

        while (i <= root && x != 2) {
            if (x % i == 0) {
                System.out.println("false " + iter);
                return (false);
            }
            else {
                iter++;
                i++;
            }
        }

        System.out.println("true " + iter);
        
        return (true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        if (!(x > 1)){
            System.out.println("IllegalArgument");
            System.exit(-1);
        }
        else {
            isPrime(x);
        }
    }
}
