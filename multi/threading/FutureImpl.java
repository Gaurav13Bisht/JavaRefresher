package multi.threading;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

public class FutureImpl {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<Integer> future = executor.submit(() -> {
            System.out.println("Returning 24 value !");
            return 24;
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Returning 24 value !");
        });

        try {
            Thread.sleep(1000);
            System.out.println(future.get());        // Execution will wait at this statement to get the result for the future variable first
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(56);
//        executor.shutdown();

        // To call multiple callables:
        Callable<Integer> callable1 = () -> {
            System.out.println("Returning 24");
            return 24;
        };

        Callable<Integer> callable2 = () -> {
            System.out.println("Returning 12");
            return 12;
        };

        Callable<Integer> callable3 = () -> {
            System.out.println("Returning 6");
            return 6;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);

        try {
            List<Future<Integer>> listFutures = executor.invokeAll(list);
            for(Future<Integer> future3 : listFutures){
                System.out.println(future3.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

    }
}
