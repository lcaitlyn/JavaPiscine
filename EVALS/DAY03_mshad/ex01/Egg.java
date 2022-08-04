public class Egg implements Runnable {
    private int count;
    ProducerConsumer producerConsumer;

    public Egg(int count, ProducerConsumer producerConsumer) {
        this.count = count;
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                producerConsumer.sayEgg();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}