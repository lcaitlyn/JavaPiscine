import java.util.Scanner;

public class Program {

    public static void isPrime(int x) {
        int root = ((int) Math.ceil(Math.sqrt(x)));

        int i = 2;

        int iter = 1;
        

        while (i <= root && x != 2) {
            if (x % i == 0) {
                System.out.println("false " + iter);
                return;
            }
            else {
                iter++;
                i++;
            }
        }
        System.out.println("true " + iter);
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