import java.util.Scanner;

public class Main {

    static final String[] DAY_OF_WEEKEND = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
    static final String ILLEGAL_ARG = "IllegalArgument";
    static final String HERE = "HERE";
    static final String NOT_HERE = "NOT_HERE";
    static final int MAX_STUDENT_AMOUNT = 10;

    public static String[] my_split(char [] charArray) {
        String [] split = new String[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            while ()
        }
    }
    // ДОДЕЛАТЬ СПЛИТ ЧТОБЫ РАЗБИВАЛ ТРЕТИЙ ПАРАГРАФ НА НУЖНЫЕ СОСТОВЯЮЩИЕ И ПОТОМ ПАРСИЛ ИХ

    public static int isDayOfWeekend(String newDay) {
        for (int i = 0; i < 7; i++) {
            if (newDay.equals(DAY_OF_WEEKEND[i]))
                return i;
        }
        return -1;
    }

    public static int isStudent(String[] students, String newStudent, int studentsSize) {
        for (int i = 0; i < studentsSize; i++) {
            if (newStudent.equals(students[i]))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = new String[MAX_STUDENT_AMOUNT];

        int [][] lessons = new int [7][6];

        int lessonsSize = 0;

        int studentsSize = 0;

        String newLine = scanner.nextLine();
        while (!newLine.equals(".")) {
            if (studentsSize >= MAX_STUDENT_AMOUNT || newLine.length() >= MAX_STUDENT_AMOUNT
                || isStudent(students, newLine, studentsSize) != -1) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }
            students[studentsSize] = newLine;
            studentsSize++;
            newLine = scanner.nextLine();
        }

        if (studentsSize == 0) {
            System.err.println(ILLEGAL_ARG);
            System.exit(-1);
        }

        newLine = scanner.nextLine();
        while (!newLine.equals(".")) {
            if (lessonsSize >= MAX_STUDENT_AMOUNT || ) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            int numOfWeek = -1;
            int numOfTime = -1;
            char [] charArray = newLine.toCharArray();
            String newDay = "";

            if (charArray[0] < '1' || charArray[0] > '6' || charArray[1] != ' ') {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            newDay += (char)charArray[2];
            newDay += (char)charArray[3];

            if (isDayOfWeekend(newDay) == -1) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            if (lessons[isDayOfWeekend(newDay)][charArray[0] - '1'] == 0) {
                lessons[isDayOfWeekend(newDay)][charArray[0] - '1']++;
                lessonsSize++;
            }

            newLine = scanner.nextLine();
        }

        if (lessonsSize == 0) {
            System.err.println(ILLEGAL_ARG);
            System.exit(-1);
        }

        newLine = scanner.nextLine();
        while (!newLine.equals(".")) {
            if (newLine.length() == 0) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }
            int [][][] diary = new int[studentsSize][7][6];

            String [] = my_split(newLine.toCharArray());

            newLine = scanner.nextLine();
        }
    }
}