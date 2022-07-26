import java.util.UUID;

public class Transaction {

    enum transferType {
        DEBIT,
        CREDIT
    }

    private final UUID identifier;
    private User sender;
    private User recipient;
    private Integer transferAmount;
    private transferType transferCategory;

    Transaction () {
        this.identifier = UUID.randomUUID();
    }

    Transaction(UUID newUuid, User recipient, User sender, Integer transferAmount, transferType transferCategory) {
        this.identifier = newUuid;
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
        this.transferCategory = transferCategory;
    }

    public UUID getIdentifier() {
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
                "identifier=" + identifier +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", transferAmount=" + transferAmount +
                ", transferCategory=" + transferCategory +
                '}';
    }
}
