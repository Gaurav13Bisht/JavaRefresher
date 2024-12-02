package multi.threading;

import java.util.Arrays;
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

        System.out.println(future.isDone());        // This will check if future is executed or not
        System.out.println(future.isCancelled());        // This will check if future is cancelled or not
        future.cancel(true);  // To cancel a future

        try {
            Thread.sleep(1000);
//            System.out.println(future.get());        // Execution will wait at this statement to get the result for the future variable first
            future.get(2, TimeUnit.SECONDS);    // This will wait for 2 seconds to get the result, if task is not executed till that point, it will throw Timeout exception
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
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

        System.out.println("-------------------------------------------------------");
        try {
            Integer i = executor.invokeAny(list);           // This will invoke all the tasks mentioned in the list but will return only the 1st completed task and cancels all remaining tasks
            System.out.println(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

    }
}
