package multi.threading;

public class Synchronisation {

    public static void main(String[] args) throws InterruptedException {
            CounterP counter = new CounterP();
            // Using anonymous class
//        Thread thread1 = new Thread(){
//            @Override
//            public void run(){
//                for(int i = 1; i <= 100; i++){
//                    counter.incrementCounter();
//                }
//            }
//        };

            Thread thread1 = new Thread(() -> {
                for(int i = 1; i <= 10000; i++){
                    counter.incrementCounter();
                }
            });

//        or we can create via :
            Runnable runnable = () ->{
                for(int i = 1; i <= 10000; i++){
                    counter.incrementCounter();
                }
            };

            Thread thread2 = new Thread(runnable);

            // Note: We can't do lambda directly for Thread class since its a class not functional interface

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            System.out.println(counter.getCount());


        }
    }


class CounterP{

    private int count;

    public CounterP(){
        this.count = 0;
    }

    // synchronized uses intrinsic locks which are implicit (second one is called extrinsic or explicit)
    public synchronized void incrementCounter(){
//      synchronized (this) {
            this.count++;       // This is CRITICAL SECTION i.e. which changes a shared resource
//       }
    }

    public int getCount(){
        return this.count;
    }

}