package ex01;

public class Printer
{
    String turn;
    Printer(String name)
    {
        turn = name;
    }
    public void waiting()
    {
        while (Thread.currentThread().getName().equals(turn))
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        turn = Thread.currentThread().getName();
        notifyAll();
    }
    public synchronized void printOut(int num)
    {
        for (int count = 0; count < num; ++count)
        {
            waiting();
            System.out.println("Now it's " + Thread.currentThread().getName() + " thread turn.");
        }
    }
}
