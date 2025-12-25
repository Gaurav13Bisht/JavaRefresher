package multi.threading.executorframework;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceImpl {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        System.out.println(LocalDateTime.now().toLocalTime().getSecond());
        // This will be executed only once after 3 seconds (delay)
        scheduledExecutorService.schedule(()->{
            System.out.println("Task 1 completed at :" + LocalDateTime.now().toLocalTime().getSecond());
        },3, TimeUnit.SECONDS);

        // This will be executed after initial delay of 4 seconds and then at each 2 seconds
        scheduledExecutorService.scheduleAtFixedRate(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 2 completed :" + LocalDateTime.now().toLocalTime().getSecond());
        }, 4, 2, TimeUnit.SECONDS);

        // This will be executed after initial delay of 4 seconds and then at each 2 seconds but the difference here in
        // AtFixedRate, next execution will start after 2 seconds of start time of previous execution but in
        // WithFixedDelay, next execution will start after 2 seconds of end time of previous execution
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 3 completed :" + LocalDateTime.now().toLocalTime().getSecond());
        }, 5, 2, TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();     // In case of scheduleAtFixedRate and scheduleWithFixedDelay, this will shutdown it too

        scheduledExecutorService.schedule(()->{
            System.out.println("Task Final completed :" + LocalDateTime.now().toLocalTime().getSecond());
            scheduledExecutorService.shutdown();
        },20, TimeUnit.SECONDS);

    }
}
