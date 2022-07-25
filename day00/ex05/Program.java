import java.util.Scanner;

public class Program {

    static final String[] DAY_OF_WEEKEND = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
    static final String ILLEGAL_ARG = "IllegalArgument";
    static final String HERE = "HERE";
    static final String NOT_HERE = "NOT_HERE";
    static final int MAX_STUDENT_AMOUNT = 10;

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

        int[][] lessons = new int[7][6];

        int lessonsSize = 0;

        int studentsSize = 0;

        int i = 0;

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
            if (lessonsSize >= MAX_STUDENT_AMOUNT) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            int numOfWeek = -1;
            int numOfTime = -1;
            char[] charArray = newLine.toCharArray();
            String newDay = "";

            if (charArray[0] < '1' || charArray[0] > '6' || charArray[1] != ' ') {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            newDay += (char) charArray[2];
            newDay += (char) charArray[3];

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

        int[][][] diary = new int[studentsSize][31][6];

        newLine = scanner.nextLine();
        while (!newLine.equals(".")) {
            if (newLine.length() == 0) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            int day = 0;
            String name = "";
            String here = "";
            int time = 0;

            i = 0;
            while (newLine.toCharArray()[i] != ' ') {
                name += newLine.toCharArray()[i];
                i++;
            }

            if (isStudent(students, name, studentsSize) == -1) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            i++;
            if (newLine.toCharArray()[i] < '1' || newLine.toCharArray()[i] > '6' || newLine.toCharArray()[i + 1] != ' ') {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            time = newLine.toCharArray()[i] - '1';
            i += 2;
            while (newLine.toCharArray()[i] != ' ') {
                day = day * 10 + (newLine.toCharArray()[i] - '0');
                i++;
            }

            if (day < 1 || day > 30) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            i++;
            while (i < newLine.length()) {
                here += newLine.toCharArray()[i];
                i++;
            }

            if (!here.equals(HERE) && !here.equals(NOT_HERE)) {
                System.err.println(ILLEGAL_ARG);
                System.exit(-1);
            }

            if (diary[isStudent(students, name, studentsSize)][day][time] == 0) {
                if (here.equals(HERE))
                    diary[isStudent(students, name, studentsSize)][day][time] = 1;
                else
                    diary[isStudent(students, name, studentsSize)][day][time] = -1;
            }
            newLine = scanner.nextLine();
        }

        System.out.printf("%10s", "");
        for (i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {
                    if (i == 0 && j == 0 || 7 * i + j > 30)
                        continue;
                    if (lessons[j][k] != 0) {
                        System.out.printf("%d:00 %s %2d|", k + 1, DAY_OF_WEEKEND[j], 7 * i + j);
                    }
                }
            }
        }
        System.out.println();

        for (i = 0; i < studentsSize; i++) {
            System.out.printf("%10s", students[i]);
            for (int j = 1; j < 31; j++) {
                for (int k = 0; k < 6; k++) {
                    if (lessons[j % 7][k] != 0) {
                        if (diary[i][j][k] != 0)
                            System.out.printf("%10d|", diary[i][j][k]);
                        else
                            System.out.printf("%10s|", "");
                    }
                }
            }
            System.out.println();
        }
        
    }
}
