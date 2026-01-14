package general;

public class SingletonClass {
    // Early initialization:
    // Since instance is created at class construction, its thread-safe by default
//    private static final SingletonClass instance = new SingletonClass();
//
//    private SingletonClass(){
//        // To avoid creation outside of class
//    }
//
//    public static SingletonClass getInstance(){
//        return instance;
//    }

    // Any required methods:



    // Lazy initialization:
    private static SingletonClass instance;

    private SingletonClass(){
    }


    public static SingletonClass getInstance(){
//        if(instance == null){                   // This alone is not thread safe, need locking
//            instance = new SingletonClass();
//        }
        // Double-checked locking
        if(instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }
}
