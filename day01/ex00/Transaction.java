import java.util.UUID;

public class Transaction {

    enum transferType {
        DEBIT,
        CREDIT
    }

    private final String identifier;
    private User recipient;
    private User sender;
    private Integer transferAmount;
    private transferType transferCategory;

    Transaction () {
        this.identifier = UUID.randomUUID().toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(transferType transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void doTransfer() {
        if (sender.getBalance() >= this.transferAmount) {
            sender.setBalance(transferAmount * -1 + sender.getBalance());
            recipient.setBalance(transferAmount + recipient.getBalance());
        }
        else
            System.err.println("Can't send money. Not enough balance.");
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier='" + identifier + '\'' +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transferAmount=" + transferAmount +
                ", transferCategory=" + transferCategory +
                '}';
    }
}