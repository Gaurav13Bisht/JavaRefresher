package multi.threading;

public class Synchronisation {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(counter);
        thread1.start();

        Thread thread2 = new Thread(counter);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());
    }
}

class Counter implements Runnable{

    private Integer count;

    public Counter(){
        count = 0;
    }

    public Integer getCount(){
        return count;
    }

    public synchronized void countIncrement(){
//        synchronized (this){
            this.count++;
//        }
    }

    @Override
    public void run(){
        for(int i = 0; i < 1000; i++) {
            countIncrement();
        }
    }
}
