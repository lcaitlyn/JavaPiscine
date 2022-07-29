import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        String[] names = {"Андрей","Антон","Денис","Вася","Петя","Коля","Саша","Маша","Наташа","Дима","Оля","Юля","Яна","Олег","Руслан","Людмила"};

        UsersArrayList users = new UsersArrayList();

        TransactionsService transactionsService = new TransactionsService();

        Transaction [] unpaired = transactionsService.getUnpairedTransactions();


        for (int i = 0; i < 3; i++) {
            users.addUser(new User(names[i], 500 + i * 50));
        }

        for (int i = 0; i < 3; i++) {
            transactionsService.addUser(users.getByIndex(i));
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(users.getByIndex(i).toString());
        }

        System.out.println("Происходят операции...");

        for (int i = 0; i < 5; i++) {
            transactionsService.doTransfer(i % 3 + 1, (i + 2) % 3 + 1, 100 * i + 50 - 25 * i);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(users.getByIndex(i).toString());
            for (Transaction t : users.getByIndex(i).getTransactionsList().toArray()) {
                System.out.println(t);
            }
        }
        System.out.println();


        if (unpaired == null)
            System.out.println("Не парные транзакции не найдены");

        System.out.println("Удаляем транзацкии у пользователя с индексом 0...");
        for (Transaction toRemove : users.getByIndex(0).getTransactionsList().toArray()) {
            users.getByIndex(0).getTransactionsList().removeTransaction(toRemove.getIdentifier());
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(users.getByIndex(i).toString());
            if (users.getByIndex(i).getTransactionsList().toArray() == null)
                continue;
            for (Transaction t : users.getByIndex(i).getTransactionsList().toArray()) {
                System.out.println(t);
            }
        }
        System.out.println();

        unpaired = transactionsService.getUnpairedTransactions();

        if (unpaired == null)
            System.out.println("Не парные транзакции не найдены");
        else {
            System.out.println("Не парные транзакции:");
            for (Transaction t : unpaired)
                System.out.println(t);
        }
    }
}
