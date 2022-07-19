public class User {
    private int balance;
    private Integer identifier;
    private String name;

    public void setBalance(int balance) {
        if (balance >= 0)
            this.balance = balance;
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

    public void printInfo() {
        System.out.println("Identifier: " + this.identifier + "\n" +
            "Name: " + this.name + "\n" +
            "Balance: " + this.balance);
    }
}
