package multi.threading;

public class ThreadCommunicationImpl {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
//        Runnable produceRunnable = new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0; i < 10; i ++){
//                    resource.produce(i);
//                }
//            }
//        };

        // Using Lambda expression
        Runnable produceRunnable = () -> {
            for(int i = 0; i < 10; i ++){
                resource.produce(i);
            }
        };

        Runnable consumeRunnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i ++){
                    resource.consume();
                }
            }
        };

        Thread thread1 = new Thread(produceRunnable);
        Thread thread2 = new Thread(consumeRunnable);

        thread1.start();
        thread2.start();
    }
}

class SharedResource {

    private Boolean hasData;
    private Integer value;

    public synchronized void produce(Integer value) {
        while (Boolean.TRUE.equals(hasData)) {    // It might look like if() should be used here because consumer thread is notifying other thread only when the data is consumed and hasData is set to false
                                                  // but there are spurious wakeups as well which is basically thread wakes up even when no notify method is called
                                                  // so to avoid this, we put this in while loop so that it again checks the flag, if its spurious wakeup then the flag will still be true so again it goes to wait state.
            try {
                wait();                            // Thread will release the lock and wait for other thread to notify and then resume the execution from this exact line
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.value = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();                  // This will notify other threads ( intended for consumer thread) to resume the execution
    }

    public synchronized Integer consume() {
        while (Boolean.FALSE.equals(hasData)) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        final Integer incomingValue = this.value;
        this.value = null;
        System.out.println("Consumed: " + incomingValue);
        return incomingValue;
    }
}