public class Program {

    public static void main(String[] args) {
        User first = new User();
        first.setBalance(1024);
        first.setIdentifier(666);
        first.setName("Биба");
        System.out.println(first.toString());
        first.setBalance(-228);
        System.out.println(first.toString());

        User second = new User();
        second.setBalance(123456);
        second.setIdentifier(777);
        second.setName("Боба");
        System.out.println(second.toString());

        System.out.println();

        Transaction transaction = new Transaction();

        transaction.setRecipient(first);
        transaction.setSender(second);
        transaction.setTransferAmount(1000);
        transaction.setTransferCategory(Transaction.transferType.DEBIT);
        transaction.doTransfer();
        System.out.println(transaction.toString());

        System.out.println();

        Transaction transactionCredit = new Transaction();
        transactionCredit.setSender(first);
        transactionCredit.setRecipient(second);
        transactionCredit.setTransferAmount(5000);
        transactionCredit.setTransferCategory(Transaction.transferType.CREDIT);
        transactionCredit.doTransfer();
        System.out.println(transactionCredit.toString());


    }
}