public class User {
    private String name;
    private int balance;
    private Integer identifier;
    private TransactionsList transactionsList = new TransactionsList();

    User(String name) {
        this.name = name;
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.balance = 0;
    }

    public boolean setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
            return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", identifier=" + identifier +
                '}';
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }
}
