public class TransactionsService {
    private UserList userList;

    public void addUser(User newUser) {
        userList.addUser(newUser);
    }

    public int getBalance(int id) throws UserNotFoundException {
        return userList.getById(id).getBalance();
    }

    public void doTransfer(int senderId, int recipientId, int amount) throws UserNotFoundException {
        if (senderId == recipientId)
            return;

        Transaction newTransaction = new Transaction(userList.getById(senderId), userList.getById(recipientId), amount, Transaction.transferType.DEBIT);

        userList.getById(senderId).getTransactionsList().addTransaction(newTransaction);
        userList.getById(recipientId).getTransactionsList().addTransaction(newTransaction);
        newTransaction.doTransfer();
    }
}
