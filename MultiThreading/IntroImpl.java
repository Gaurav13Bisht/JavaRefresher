package MultiThreading;

public class IntroImpl {
    public static void main(String[] args) throws InterruptedException {
//        lifeCycle();
        threadMethods();
    }

    private static void threadMethods() throws InterruptedException {
        // start, run, join

        // sleep
        Thread.sleep(1000);

        // getState, getName
        System.out.println(Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getName());

        // getPriority, setName
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        Thread.currentThread().setName("Gaurav Thread");

        // interrupt: This will not directly stop the thread execution (or waiting) but will
        //            set the flag "interrupted" = true which is then periodically checked by the thread itself
        //            and when it finds it to be TRUE, it stops the current execution of the thread.
//              Note: It will not mandatory stop the thread, it just throws the InterruptedException which if required
//                    can be handled as well in catch block and thus thread can be still alive afterwards and execute the later code.
//                     If not caught, it will Terminate the thread.
        Custom customThread = new Custom();
        customThread.start();
//        customThread.interrupt();
        customThread.join();
        System.out.println(customThread.getState());

        // Thread.yield(): This is used inside run() method to ask JVM that it can assign the resource or CPU time
        //                  to another thread

        // setDaemon(): Used to make a custom thread as Daemon ie background thread for which JVM doesn't
        //              wait to get completed e.g. Daemon thread does Garbage collection. logging, monitoring etc.
        //              customThread2 also have the sleep() method but JVM will not wait for it since its Daemon threa
        //              and closes the application so "custom: Completed" will not print for this thread
        Custom customThread2 = new Custom();
        customThread2.setDaemon(true);
        System.out.println("Starting user thread as Daemon !!");
        customThread2.start();
        System.out.println("End of application !!");
    }


    public static void lifeCycle() throws InterruptedException {
        // LIFECYCLE OF THREADS
        System.out.println("main: " + Thread.currentThread().getState());   // Main thread: RUNNABLE

        Custom custom = new Custom();

        System.out.println("custom: " + custom.getState());              // Custom thread: NEW

        custom.start();

        System.out.println("custom: " + custom.getState());              // Custom thread: RUNNABLE

        Thread.sleep(1000);

        System.out.println("custom: " + custom.getState());             // Custom thread: TIMED_WAITING

        custom.join();    // This will make the main thread wait at this line for the custom thread to complete its execution

        System.out.println("custom: " + custom.getState());             // Custom thread: TERMINATED

        System.out.println("Inside Thread: " + Thread.currentThread().getName());
    }
}

class Custom extends Thread{

    // To assign a custom name to the thread at time of creation
//    public Custom(String name){
//        super(name);
//    }

    @Override
    public void run(){
        System.out.println("custom: " + this.getState());         // Custom Thread: Runnable
//        System.out.println("custom: " + Thread.currentThread().getState());
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
//        Thread.yield();
        try {
            Thread.sleep(2000);
            System.out.println("custom: " + "Completed");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e);
        }
    }
}