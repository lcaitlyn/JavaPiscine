import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        String[] names = {"Андрей","Антон","Денис","Вася","Петя","Коля","Саша","Маша","Наташа","Дима","Оля","Юля","Яна","Олег","Руслан","Людмила"};

        UsersArrayList users = new UsersArrayList();


        for (int i = 0; i < 3; i++) {
            users.addUser(new User(names[i]));
            users.getByIndex(i).setBalance(100 * i + 50);
        }

        for (int i = 0; i < users.getUsersNumber(); i++) {
            System.out.println(users.getByIndex(i).toString());
        }

        for (int i = 0; i < 10; i++) {
            User sender = users.getByIndex(i % 3);
            User receipter = users.getByIndex((i + 1) % 3);
            int amount = users.getByIndex(i % 3).getBalance() % 30 + 20;

            Transaction newTransaction = new Transaction(sender, receipter, amount, Transaction.transferType.DEBIT);

            if (sender.getIdentifier() == receipter.getIdentifier())
                continue;

            sender.getTransactionsList().addTransaction(newTransaction);
            receipter.getTransactionsList().addTransaction(newTransaction);
            newTransaction.doTransfer();
        }

        System.out.println("Actions...");

        for (int i = 0; i < users.getUsersNumber(); i++) {
            System.out.println(users.getByIndex(i).toString());
        }

        System.out.println("User's {name=" + users.getByIndex(0).getName() + "} Transfer list:");
        Transaction [] transactionsArray = users.getByIndex(0).getTransactionsList().toArray();

        for (int i = 0; i < users.getByIndex(0).getTransactionsList().getTransactionsNumber(); i++) {
            System.out.println(transactionsArray[i].toString());
        }

        users.getByIndex(0).getTransactionsList().removeTransaction(users.getByIndex(2).getTransactionsList().toArray()[0].getIdentifier());
        users.getByIndex(0).getTransactionsList().removeTransaction(users.getByIndex(2).getTransactionsList().toArray()[2].getIdentifier());

        System.out.println("User's {name=" + users.getByIndex(0).getName() + "} Transfer List After Deleting:");

        for (int i = 0; i < users.getByIndex(0).getTransactionsList().getTransactionsNumber(); i++) {
            System.out.println(transactionsArray[i].toString());
        }

        System.out.println("Time to die ☹️");
        users.getByIndex(1).getTransactionsList().removeTransaction(users.getByIndex(0).getTransactionsList().toArray()[1].getIdentifier());
    }
}
