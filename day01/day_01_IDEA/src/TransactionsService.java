import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList = new UsersArrayList();

    public void addUser(User newUser) {
        userList.addUser(newUser);
    }

    public int getBalance(int id) throws UserNotFoundException {
        return userList.getById(id).getBalance();
    }

    public void doTransfer(int senderId, int recipientId, int amount) throws UserNotFoundException, IllegalTransactionException {
//        if (senderId == recipientId)
//            return;
//
//        if (userList.getById(senderId).getBalance() < amount) {
//            throw new IllegalTransactionException();
//        }
//
//        Transaction newTransaction = new Transaction(userList.getById(senderId), userList.getById(recipientId), amount, Transaction.transferType.DEBIT);
//
//        userList.getById(senderId).getTransactionsList().addTransaction(newTransaction);
//        userList.getById(recipientId).getTransactionsList().addTransaction(newTransaction);
//        newTransaction.doTransfer();
    }

    public Transaction [] getTransactions (int userId) {
        return userList.getById(userId).getTransactionsList().toArray();
    }

    public void removeTransaction(UUID transactionId, int userId) {
        userList.getById(userId).getTransactionsList().removeTransaction(transactionId);
    }

//    public Transaction [] getUnpairedTransactions () {
//        Transaction [] transactions = new Transaction [10];
//
//        for (int i = 0; i < userList.getUsersNumber(); i++) {
//            for (Transaction newTransaction : userList.getByIndex(i).getTransactionsList().toArray()) {
//                int i = 0;
//                for (Transaction transaction : transactions) {
//                    if (transaction.getIdentifier() == newTransaction.getIdentifier()) {
//                        i = 1;
//                    }
//                }
//                if (i != 1) {
//                    transactions.
//                }
//            }
//        }
//
//    }
}
