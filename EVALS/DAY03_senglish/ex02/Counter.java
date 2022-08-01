package ex02;

public class Counter
{
    private Integer sum;
    String  turn;
    Counter(String num)
    {
        sum = 0;
        turn = num;
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
    public synchronized void count(int[] array)
    {
        int len;
        len = array.length;
        waiting();
        int num = 0;
        for (int count = 0; count < len; ++count)
        {
            num += array[count];
        }
        this.sum += num;
    }
    public int getSum()
    {
        return this.sum;
    }
}