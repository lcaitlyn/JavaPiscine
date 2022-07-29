import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList = new UsersArrayList();

    public void addUser(User newUser) {
        userList.addUser(newUser);
    }

    public int getBalance(int id) throws UserNotFoundException {
        return userList.getById(id).getBalance();
    }

    public UsersArrayList getUserList() {
        return userList;
    }

    public void doTransfer(int senderId, int recipientId, int amount) throws UserNotFoundException, IllegalTransactionException {
        if (senderId == recipientId)
            return;

        if (userList.getById(senderId).getBalance() < amount) {
            throw new IllegalTransactionException();
        }

        UUID uuid = UUID.randomUUID();

        Transaction debitTransaction = new Transaction(uuid, userList.getById(senderId), userList.getById(recipientId), amount, Transaction.transferType.DEBIT);
        Transaction creditTransaction = new Transaction(uuid, userList.getById(senderId), userList.getById(recipientId), amount, Transaction.transferType.CREDIT);

        userList.getById(senderId).getTransactionsList().addTransaction(debitTransaction);
        userList.getById(recipientId).getTransactionsList().addTransaction(creditTransaction);
        creditTransaction.doTransfer();
    }

    public Transaction [] getTransactions (int userId) {
        return userList.getById(userId).getTransactionsList().toArray();
    }

    public void removeTransaction(UUID transactionId, int userId) {
        userList.getById(userId).getTransactionsList().removeTransaction(transactionId);
    }

    public Transaction [] getUnpairedTransactions () {
        TransactionsLinkedList all = new TransactionsLinkedList();
        TransactionsLinkedList unpaired = new TransactionsLinkedList();

        for (int i = 0; i < userList.getUsersNumber(); i++) {
            if (userList.getByIndex(i).getTransactionsList().toArray() == null)
                continue;

            for (Transaction t : userList.getByIndex(i).getTransactionsList().toArray()) {
                all.addTransaction(t);
            }
        }

        for (int i = 0; i < all.getTransactionsNumber(); i++) {
            int credit = 0;
            int debit = 0;

            if (all.toArray()[i].getTransferCategory() == Transaction.transferType.CREDIT)
                credit++;
            else if (all.toArray()[i].getTransferCategory() == Transaction.transferType.DEBIT)
                debit++;

            for (int j = 0; j < all.getTransactionsNumber(); j++) {
                if (all.toArray()[i].getIdentifier() == all.toArray()[j].getIdentifier()) {
                    if (all.toArray()[j].getTransferCategory() == Transaction.transferType.CREDIT)
                        credit++;
                    else if (all.toArray()[j].getTransferCategory() == Transaction.transferType.DEBIT)
                        debit++;
                }
            }

            if (credit == 0 || debit == 0) {
                if (unpaired.getTransaction(all.toArray()[i].getIdentifier()) == null) {
                    unpaired.addTransaction(all.toArray()[i]);
                }

            }
        }
        return unpaired.toArray();
    }
}
