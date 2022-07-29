public class IllegalTransactionException extends RuntimeException{
    IllegalTransactionException () {
        System.err.println("Not enough money");
    }
}
