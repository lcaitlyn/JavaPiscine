public class Program {

    public static int getSum(int x) {
        int sum = 0;

        sum += x / 100000;

        x %= 100000;

        sum += x / 10000;

        x %= 10000;

        sum += x / 1000;

        x %= 1000;

        sum += x / 100;

        x %= 100;

        sum += x / 10;

        x %= 10;

        sum += x;

        return sum;        
    }

    public static void main(String[] args) {
        System.out.println(getSum(123456));
        System.out.println(getSum(479598));
    }
}
