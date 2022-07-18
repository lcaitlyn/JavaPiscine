public class Program {

    public static int getSumm(int x) {
        int sum = 0;

        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getSumm(123456));
        System.out.println(getSumm(479598));
    }
}
