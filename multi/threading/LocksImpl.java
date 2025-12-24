package multi.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksImpl {
    public static void main(String[] args) {
        Transfer transfer = new Transfer();
        Thread threadA = new Thread(transfer, "Thread A");
        Thread threadB = new Thread(transfer, "Thread B");

        threadA.start();
        threadB.start();
    }
}

class Transfer implements Runnable{

    @Override
    public void run(){
        transferMoney();
    }

    private final Lock lock = new ReentrantLock();

     // Achieving Mutual exclusion using Explicit Locks
    public void transferMoney() {
        try {
            // This will wait till the lock is released by the other thread if acquired
            // lock.lock();

            // This will check if the lock is not acquired by other thread, if true it acquire then return true, if false just return false.
            // if(lock.tryLock()){

            // This will check if the lock is not acquired by other thread and acquire it and return true else wait for the given
            // time period and keeps checking and return true if lock is free otherwise returns false if time period is over
            if (lock.tryLock(4000, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " is sending 50$ to beneficiary !!");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " has sent the money !!");
            } else {
                System.out.println(Thread.currentThread().getName() + ": Payment system busy ! Please Try Later");
            }
        }
        catch (InterruptedException e){
            System.out.println("Exception caught");
        }
        finally{
            // This will release the acquired lock by the current thread for other thread's use
            lock.unlock();
        }
    }

    // Achieving Mutual exclusion using "synchronized" keyword which have its intrinsic locks that works automatically
//    public synchronized void transferMoney() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " is sending 50$ to beneficiary !!");
//        Thread.sleep(3000);
//        System.out.println(Thread.currentThread().getName() + " has sent the money !!");
//    }
}