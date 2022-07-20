public class TransactionNode {
    private Transaction data;
    private TransactionNode next;
    private TransactionNode prev;

    public TransactionNode(Transaction data) {
        this.data = data;
    }

    public Transaction getData() {
        return data;
    }

    public void setData(Transaction data) {
        this.data = data;
    }

    public TransactionNode getNext() {
        return next;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public TransactionNode getPrev() {
        return prev;
    }

    public void setPrev(TransactionNode prev) {
        this.prev = prev;
    }
}
