package multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocksImpl {
    public static void main(String[] args) throws InterruptedException {
        Counter2 counter2 = new Counter2();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    counter2.readCount();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    counter2.incrementCount();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1 = new Thread(writeRunnable, "Thread W");
        Thread thread2 = new Thread(readRunnable, "Thread R1");
        Thread thread3 = new Thread(readRunnable, "Thread R2");

        thread1.start();
        Thread.sleep(50);
        thread2.start();
        Thread.sleep(50);
        thread3.start();
    }
}

class Counter2{

    private Integer count = 0;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

//    private final Lock lock = new ReentrantLock();

    public void incrementCount() throws InterruptedException {
//        lock.lock();

        // This wont let any other thread acquire it and neither any readLocks to be acquired by other threads
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + ": Incrementing");
        for (long i = 0; i < 1000; i++){
            this.count++;
        }
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ": Incremented");
//        lock.unlock();
        writeLock.unlock();
    }

    public void readCount() throws InterruptedException {
        // Here even tho the operation is read only, thread3 will have to wait for thread2 before reading the resource.
//        lock.lock();

        // Now, since this is a read lock, thread3 will not have to wait for thread2 since it is reading the resource only
        // Given that thread1 has not acquired the write Lock, if write Lock is acquired, read locks wait for it to be released
        readLock.lock();

        System.out.println(Thread.currentThread().getName() + ": value = " + this.count);
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " Exiting");
//        lock.unlock();

        readLock.unlock();
    }

//    ReadWriteLock Behavior
//    WRITELock  → blocks READ + WRITELock
//    READLock   → blocks WRITELock only
//    READ + READ → allowed
}