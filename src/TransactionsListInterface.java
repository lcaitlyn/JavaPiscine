import java.util.UUID;

public interface TransactionsListInterface {
    void addTransaction(Transaction newTransaction);
    void removeTransaction(UUID identifier);
    Transaction [] toArray();
}
