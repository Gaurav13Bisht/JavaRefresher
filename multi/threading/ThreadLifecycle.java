package multi.threading;

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {

        Custom3 thread = new Custom3();
        System.out.println("Inside main custom 1 " + thread.getState());                // NEW
        System.out.println("Inside main MAIN 1 " + Thread.currentThread().getState());  // RUNNABLE
        thread.start();
//        thread.run();  // If used this then it do not create a new thread but passes the control to the run method and
        // does sequential processing, so its like calling a normal method.

        Thread.sleep(1000);
        System.out.println("Inside main custom 2 " + thread.getState());                // TIMED_WAITING
        Thread.sleep(3000);
        System.out.println("Inside main custom 3 " + thread.getState());                // WAITING
        System.out.println("Inside main MAIN 3 " + Thread.currentThread().getState());  // RUNNABLE
        thread.join();

        System.out.println("Inside main MAIN after join 4 " + Thread.currentThread().getState()); // RUNNABLE
        System.out.println("Inside main custom after join 4 " + thread.getState()); // TERMINATED


        Thread daemon = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                System.out.println("Daemon running");     // Will not be printed
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        System.out.println("Main finished");          // Will print and app shutdown, wont wait because its daemon thread
    }
}


class Custom3 extends Thread{

    @Override
    public void run() {
        System.out.println("Inside run Custom3 " + currentThread().getName());
        System.out.println("Inside run Custom3 " + currentThread().getState());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught Custom3!");
        }

        Thread thread = new Thread(new Custom4());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Inside run completed");
    }

}

class Custom4 implements Runnable{

    @Override
    public void run() {
        System.out.println("Inside run Custom4 " + Thread.currentThread().getName());
        System.out.println("Inside run Custom4 " + Thread.currentThread().getState());
//        Thread.currentThread().interrupt();   // This will not throw InterruptedException immediately, it just mark the thread as interrupted
        // and then jvm periodically checks the flag and if its true, it throw the InterruptedException.
        // Thats why during Thread.sleep(3000), jvm checks the flag and throws InterruptedException thus we get "Interrupted exception caught Custom4!"
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught Custom4!");
        }

        System.out.println("Inside run completed Custom4");
    }

}