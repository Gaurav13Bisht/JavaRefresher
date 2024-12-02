package multi.threading.executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceImpl {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(()->{
            System.out.println("This will run after 3 seconds !");
        },3, TimeUnit.SECONDS);

        // Schedules a task to start at a fixed interval between the start of successive executions.
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("This will run after 4 seconds of each start time and every 2 seconds !");
        }, 4, 2, TimeUnit.SECONDS);

        // Schedules a task to execute with a fixed delay between the end of one execution and the start of the next.
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            System.out.println("This will run after 5 seconds of each end time and with 3 seconds delay!");
        }, 5, 3, TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();     // In case of scheduleAtFixedRate and scheduleWithFixedDelay, this will shutdown it too

        scheduledExecutorService.schedule(()->{
            System.out.println("Shutting down after 10 seconds !");
            scheduledExecutorService.shutdown();
        },10, TimeUnit.SECONDS);

    }
}
