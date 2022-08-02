public class MyThreadEgg extends Thread{
    private int size;

    public MyThreadEgg(int size) {
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            System.out.println("Egg");
        }
    }
}
