public class MultiThreadingImpl {
    public static void main(String[] args) throws InterruptedException {
//        lifeCycle();
        threadMethods();
    }

    private static void threadMethods() throws InterruptedException {
        // start, run, join

        // sleep
        Thread.sleep(2000);

        // getState, getName
        Thread.currentThread().getState();
        Thread.currentThread().getName();

        // getPriority, setName
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        Thread.currentThread().setName("Gaurav Thread");


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
        try {
            Thread.sleep(2000);
            System.out.println("custom: " + "Completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}