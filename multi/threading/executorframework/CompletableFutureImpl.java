package multi.threading.executorframework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureImpl {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
            System.out.println("Starting supply");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Supplied it !");
            return 20;
        });

        CompletableFuture<Integer> cf2 = cf.thenApply(x -> {    // This will start executing when cf is completed and this doesnt block the thread
            System.out.println("Starting to Square !");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Squared it");
            return x * x;
        });

        System.out.println("Not waiting !!");

        System.out.println(cf2.get());  // This will block the thread for cf2 execution

        System.out.println("Shutting down");

    }
}
