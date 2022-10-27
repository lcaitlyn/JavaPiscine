package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int x) {
        if (x < 2)
            throw new IllegalNumberException();

        int root = (int) Math.ceil(Math.sqrt(x));

        for (int i = 2; i <= root && x != 2 && x != 3; i++) {
            if (x % i == 0)
                return false;
        }
        return (true);
    }

    public int digitsSum(int number) {
        String s = Integer.toString(number);
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            sum += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return sum;
    }
}
