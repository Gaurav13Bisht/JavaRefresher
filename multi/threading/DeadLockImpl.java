package multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockImpl {
    public static void main(String[] args) {
        PersonA personA = new PersonA();
        PersonB personB = new PersonB();

        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                personA.transfer(personB);
            }
        };

        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                personB.transfer(personA);
            }
        };

        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);

        threadA.start();
        threadB.start();
    }
}

class PersonA {
    private final Lock lock = new ReentrantLock();

    // Either we can put lock.lock() at start and end of method or just put "synchronised" keyword at both methods

    public void transfer(PersonB personB){
        // This will cause DEADLOCK since both are waiting for the respective class's lock to be released.
//        lock.lock();

        // Now this thread can only acquire this lock when personB's lock is free to be acquired
        // So at first, since both threads are started simultaneously, personB's thread lock would already be acquired so
        // the current thread will not acquire the current block's lock and wait.
        // Now in method of personB, since this block's lock is not acquired, it will acquire this lock and execute the code and
        // then release both the locks (both classes) which will be then freed for this class's thread to acquire
        // Thus DEADLOCK will be solved
        synchronized (personB) {
            System.out.println("Transferring to personB");
            personB.getAccountNumber();
            System.out.println("Transferred to personB");
        }
//        lock.unlock();
    }

    public Integer getAccountNumber(){
        lock.lock();
        System.out.println("Giving acct number to personB");
        lock.unlock();
        return 12345;
    }
}

class PersonB{

    private final Lock lock = new ReentrantLock();

    // Either we can put lock.lock() at start and end of method or just put "synchronised" keyword at both methods

    public void transfer(PersonA personA){
//        lock.lock();
        synchronized (personA) {
            System.out.println("Transferring to personA");
            personA.getAccountNumber();
            System.out.println("Transferred to personA");
        }
//        lock.unlock();
    }

    public Integer getAccountNumber(){
        lock.lock();
        System.out.println("Giving acct number to personA");
        lock.unlock();
        return 12345;
    }
}
