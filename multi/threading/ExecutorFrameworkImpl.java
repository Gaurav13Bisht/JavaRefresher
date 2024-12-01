package multi.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFrameworkImpl {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(9);  // Here we can mention the number of threads required

        for(int i = 1; i < 10; i++){
            int finalI = i;
            executorService.submit(()->{
                int factorial = factorial(finalI);
                System.out.println(factorial);
            });
        }

        executorService.shutdown();     // This will order the service to shut down and moves to the next line of code for execution
                                        // It closes the thread pool, if shutdown is not used, program will not terminate
                                        // This will not shutdown immediately but go for Graceful shutdown i.e. shutdown after all the threads work is done

//        executorService.shutdownNow();   // This will force shutdown when this line gets executed even if threads has not done the work yet

        try {
            // This will wait for the termination of service which we ordered with executorService.shutdown() for 3 seconds
            // the execution will wait here for 3 seconds if its terminated then return true and proceed with further execution of lines otherwise will return false
            if (executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("All tasks completed within the timeout.");
            } else {
                System.out.println("Timeout elapsed before completion. Force shutting down.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int factorial(int n){
        if(n == 1 || n == 0){
            return 1;
        }
        return n * factorial(n - 1);
    }
}
