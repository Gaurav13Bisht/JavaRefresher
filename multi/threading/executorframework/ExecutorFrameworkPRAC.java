package multi.threading.executorframework;

import com.sun.security.jgss.GSSUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorFrameworkPRAC {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(9);

//        executorService.submit(() ->{
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Bhai h");
//        });
//
//        executorService.shutdown();
//
//        if(executorService.awaitTermination(3, TimeUnit.SECONDS)){
//            System.out.println("Completed within 3 seconds");
//        }
//        else{
//            System.out.println("Not completed within 3 seconds, force shutdown!");
//            executorService.shutdownNow();
//        }
//
//        System.out.println("Exiting!");


        // FUTURE:
//        Future<?> future = executorService.submit(() ->{
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Bhai h");               // This is runnable implementation since it doesnt return anything
//        });
//
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        future.get();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//
//        if(executorService.awaitTermination(3, TimeUnit.SECONDS)){
//            System.out.println("Completed within 3 seconds");
//        }
//        else{
//            System.out.println("Not completed within 3 seconds, force shutdown!");
//            executorService.shutdownNow();
//        }
//
//        System.out.println("Exiting!");


//         Callable:
//        Future<String> future = executorService.submit(() ->{
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return "Bhai h";                         // This is callable implementation because it returns something
//        });
//
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        System.out.println(future.get());
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//
//        if(executorService.awaitTermination(3, TimeUnit.SECONDS)){
//            System.out.println("Completed within 3 seconds");
//        }
//        else{
//            System.out.println("Not completed within 3 seconds, force shutdown!");
//            executorService.shutdownNow();
//        }
//
//        System.out.println("Exiting!");


        // InvokeAll

//        Callable<Integer> callable = () ->{
//            return 1;
//        };
//        Callable<Integer> callable2 = () ->{
//            return 2;
//        };
//        Callable<Integer> callable3 = () ->{
//            return 2;
//        };
//
//        List<Future<Integer>> futureList = executorService.invokeAll(Arrays.asList(callable, callable2));
//
//        Integer value = executorService.invokeAny(Arrays.asList(callable, callable2));
//
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        System.out.println(futureList.get(0).get());
//        System.out.println(futureList.get(1).get());
//        Thread.sleep(2000);
//
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
////        Integer value = executorService.invokeAny(Arrays.asList(callable, callable2));       // Error here because executorService is already shutdown
//        System.out.println(value);
//
//        if(executorService.awaitTermination(3, TimeUnit.SECONDS)){
//            System.out.println("Completed within 3 seconds");
//        }
//        else{
//            System.out.println("Not completed within 3 seconds, force shutdown!");
//            executorService.shutdownNow();
//        }
//
//        System.out.println("Exiting!");


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(() ->{
            System.out.println("Bhai h1");
        }, 3, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() ->{
            System.out.println("Bhai h2");
        }, 0, 3, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(() ->{
            System.out.println("Bhai h3");
        }, 0, 3, TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();

    }

}
