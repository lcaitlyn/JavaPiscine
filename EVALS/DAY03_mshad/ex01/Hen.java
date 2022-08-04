public class Hen implements Runnable {
    private int count;
    ProducerConsumer producerConsumer;

    public Hen(int count, ProducerConsumer producerConsumer) {
        this.count = count;
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                producerConsumer.sayHen();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}