import java.util.Scanner;

public class Program {

    public static int[][] bubbleSort(int[][] arr) {
        int[] tmp;

        for(int i = arr.length-1 ; i > 0 ; i--) {
            for(int j = 0; j < i; j++) {
                if (arr[j][1] < arr[j + 1][1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        for(int i = arr.length-1 ; i > 0 ; i--) {
            for(int j = 0; j < i; j++) {
                if (arr[j][1] == arr[j + 1][1] && (int)arr[j][0] > (int)arr[j + 1][0]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }

    public static void sorting(int[] arr, int size) {
        int max = 0;

        int j = 0;

        int[][] sorted = new int[size][3];

        for (int i = 0; i < 65536 && j < size; i++) {
            if (arr[i] != 0) {
                sorted[j][0] = i;
                sorted[j][1] = arr[i];
                j++;
            }
        }

        sorted = bubbleSort(sorted);

        if (size > 10)
            size = 10;

        max = sorted[0][1];

        for (int i = 0; i < size; i++) {
            sorted[i][2] = (int)(sorted[i][1] * 10 / max);
        }

        for (int counter = 12; counter > 0; counter--) {
            for (int i = 0; i < size; i++) {
                if (counter == 1 && i < size)
                    System.out.print((char)sorted[i][0] + "  ");
                else if (counter == 1 && i + 1 == size)
                    System.out.println((char)sorted[i][1]);
                else if (sorted[i][2] + 2 == counter && i < size)
                    System.out.print(sorted[i][1] + "  ");
                else if (sorted[i][2] + 2 == counter && i == size)
                    System.out.print(sorted[i][1]);
                else if (sorted[i][2] + 1 >= counter && i < size)
                    System.out.print("#  ");
                else if (sorted[i][2] + 1 <= counter && i == size)
                    System.out.print("#");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        int size = 0;

        char[] arr;

        int[] alp = new int[65536];

        int[] sort = new int[10];

        arr = str.toCharArray();

        if (str.length() > 999) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        for (int i = 0; i < str.length(); i++) {
            if ((int)arr[i] > 65535)
                System.exit(-1);
            else {
                if (alp[arr[i]] == 0)
                    size++;

                alp[arr[i]]++;
            }
        }

        if (arr.length == 0 || size == 0)
            System.exit(-1);

        sorting(alp, size);

        sc.close();
    }
}
