import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException() {
        System.err.println("Transaction Not Found");
    }

    TransactionNotFoundException(UUID id) {
        System.err.println("Transaction {" + id.toString() + "} Not Found");
    }
}
