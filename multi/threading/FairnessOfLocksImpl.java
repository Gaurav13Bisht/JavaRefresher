package multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessOfLocksImpl {
    public static void main(String[] args) throws InterruptedException {
        Helper helper = new Helper();
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                helper.test();
            }
        };

        Thread thread1 = new Thread(runnable, "T1");
        Thread thread2 = new Thread(runnable, "T2");
        Thread thread3 = new Thread(runnable, "T3");

        thread1.start();
        Thread.sleep(50);
        thread2.start();
        Thread.sleep(50);
        thread3.start();
    }
}

class Helper{

//    private final Lock lock = new ReentrantLock();
    private final Lock lock = new ReentrantLock(true);

    // Background:
    // Thread 1 gets the lock first since its first called and then thread is sleep before thread2 execution and thread3 execution
    // Thread 1 sleeps for 2 seconds in the method and meanwhile both thread 2 and thread 3 are ready to acquire the lock next

    // Behaviour:
    // 1. Un-fair lock: There isn't any fixed priority that thread 2 or thread 3 which one will acquire the lock next
    // 2. Fair lock: That thread who came first will acquire the lock first i.e. thread 2 in this case

    // Note: In case of un-fair lock, thread 2 is getting the lock before thread 3 each time which is because of the Thread scheduler of JVM which prioritise the first come
    // not by the property of unfair lock

    public void test(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": Waiting for process to complete.....");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": Process completed.");
        }
        catch(Exception e){
            Thread.currentThread().interrupt();
        }
        finally {
            lock.unlock();
        }
    }
}