package multi.threading.executorframework;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureImpl {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now().toLocalTime().getSecond());
//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(LocalDateTime.now().toLocalTime().getSecond());
//            return 100;
//        });             // If we don't pass any executor then by default it uses ForkJoinPool.commonPool for threads which have
//                        // thread pool of size equal to number of CPU Cores
//
//        // Or we can pass our executor to it and it will use the defined threadPool
////        ExecutorService executorService = Executors.newFixedThreadPool(9);
////        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()->{
////            try {
////                Thread.sleep(2000);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
////            System.out.println(LocalDateTime.now().toLocalTime().getSecond());
////            return 100;
////        }, executorService);

        // IMPORTANT:
        // We have 3 main methods to use on a completableFuture:
        // 1. thenApply() : This takes the response from the completableFuture and returns a new completableFuture with a response/new returned value.
        // 2. thenAccept() : This takes the response from the completableFuture and returns a new completableFuture with NO response/new returned value.
        // 3. thenRun() : This doesn't take the response from the completableFuture and returns a new completableFuture with NO response/new returned value.

//        CompletableFuture<Integer> finalCompletableFuture = completableFuture.thenApply((x)->{      // Can use thenApplyAsync() as well which executes in new thread otherwise thenApply executes in same thread that the completableFuture used
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(LocalDateTime.now().toLocalTime().getSecond());
//            return x*x;
//        });
//
//        System.out.println("Not waiting");
//
//        finalCompletableFuture.thenAccept((x)->{  // This non-blocking
//            System.out.println("Result is: " + x);
//        });
//
////        System.out.println(finalCompletableFuture.join());    / /This is blocking
//
//        System.out.println("Not waiting");
//        Thread.sleep(5000);        // needed else app will close before final result print


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future1 executed");
            return "Result 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            throw new RuntimeException("");
            System.out.println("future2 executed");
            return "Result 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future3 executed");
            return "Result 3";
        });

        System.out.println("Not waiting !");

//        System.out.println("allFutures = " + future1.join() + " " + future2.join() + " " + future3.join()); // blocking
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

        // Non-blocking
        allFutures.exceptionally(ex ->{
            System.out.println("EncounteredException : " + ex.getClass().getCanonicalName());
            return null;
        }).thenAccept(x -> {
            System.out.println("allFutures = " + future1.join() + " " + future2.join() + " " + future3.join());
        });

        // Non-blocking
//        allFutures.exceptionally(ex ->{
//            System.out.println("EncounteredException : " + ex.getClass().getCanonicalName());
//            return null;
//        }).thenRun(() -> {
//            System.out.println("allFutures = " + future1.join() + " " + future2.join() + " " + future3.join());
//        });

        System.out.println("Not waiting !");

        Thread.sleep(4000);
        System.out.println("Shutdown");



//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            int result = 10 / 0; // Causes an ArithmeticException
//            return result;
//        });
//
//        future.exceptionally(ex -> {
//            System.out.println("Exception occurred: " + ex.getMessage());
//            return 0; // Default value to return if there's an exception
//        }).thenAccept(result -> {
//            System.out.println("Result: " + result);
//        });
    }


    // Multiple Async rest call example:
//    @Service
//    public class AggregatorService {
//        @Autowired
//        private AsyncRestTemplate restTemplate;
//
//        public CompletableFuture<AggregatedResponse> getAggregatedResponse() {
//            CompletableFuture<User[]> usersFuture = CompletableFuture.supplyAsync(() -> {
//                return restTemplate.getForObject("http://localhost:8080/users", User[].class);
//            });
//
//            CompletableFuture<Product[]> productsFuture = CompletableFuture.supplyAsync(() -> {
//                return restTemplate.getForObject("http://localhost:8080/products", Product[].class);
//            });
//
//            CompletableFuture<Order[]> ordersFuture = CompletableFuture.supplyAsync(() -> {
//                return restTemplate.getForObject("http://localhost:8080/orders", Order[].class);
//            });
//
//            return CompletableFuture.allOf(usersFuture, productsFuture, ordersFuture)
//                    .thenApply(v -> new AggregatedResponse(usersFuture.join(), productsFuture.join(), ordersFuture.join()));
//        }
//    }
}
