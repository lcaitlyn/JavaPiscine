public class User {
    private String name;
    private int balance;
    private Integer identifier;

    public boolean setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
            return true;
        }
        return false;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
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
}