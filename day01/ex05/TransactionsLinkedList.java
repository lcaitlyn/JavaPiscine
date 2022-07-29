import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Integer transactionsNumber = 0;
    private TransactionNode head = new TransactionNode(null);

    public Integer getTransactionsNumber() {
        return transactionsNumber;
    }

    @Override
    public void addTransaction(Transaction newTransaction) {
        TransactionNode newTrasactionNode = new TransactionNode(newTransaction);

        if (head != null) {
            head.setPrev(head);
        }
        newTrasactionNode.setPrev(null);
        newTrasactionNode.setNext(head);
        head = newTrasactionNode;
        transactionsNumber++;
    }

    @Override
    public void removeTransaction(UUID identifier) {
        TransactionNode tmp = head;

        for (int i = 0; i < transactionsNumber; i++) {
            if (tmp.getData().getIdentifier().equals(identifier)) {
                if (tmp.getPrev() != null) {
                    tmp.getPrev().setNext(tmp.getNext());
                } else {
                    head = tmp.getNext();
                }
                if (tmp.getNext() != null) {
                    tmp.getNext().setPrev(tmp.getPrev());
                }
                transactionsNumber--;
                return;
            }
            tmp = tmp.getNext();
        }
        throw new TransactionNotFoundException(identifier);
    }

    @Override
    public  Transaction [] toArray() {
        TransactionNode tmp = head;

        if (transactionsNumber == 0 || tmp == null)
            return null;

        Transaction [] array = new Transaction[transactionsNumber];

        int i = 0;

        for (i = 0; i < transactionsNumber; i++) {
            array[i] = tmp.getData();
            tmp = tmp.getNext();
        }
        return array;
    }

    @Override
    public Transaction getTransaction(UUID identifier) {
        TransactionNode tmp = head;

        if (transactionsNumber == 0 || tmp == null)
            return null;

        for (int i = 0; i < transactionsNumber; i++) {
            if (tmp.getData().getIdentifier().equals(identifier)) {
                System.out.println("tmp = " + tmp.getData().getIdentifier() + " identifier = " + identifier);
                return tmp.getData();
            }

        }
        return null;
    }
}
