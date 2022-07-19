public class Main {

    public static void main(String[] args) {
        User first = new User();
        first.setBalance(1024);
        first.setIdentifier(666);
        first.setName("Руслан");
        first.printInfo();
        first.setBalance(-228);
        first.printInfo();

        
    }
}
