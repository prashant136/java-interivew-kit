package _01_CoreJava;

/// ---------------------------- Singleton Pattern -------------------------------
// Singleton ensure that only a single object of a class exists throughout the entire application.
// eager initialization
class CalculatorEager {
    public int a, b;

    private static CalculatorEager obj = new CalculatorEager();

    private CalculatorEager() {}

    public int sum() {
        return a + b;
    }

    public static CalculatorEager getInstance() {
        return obj;
    }
}

/// -------- Lazy Initialization Approach --------
// this is singleton class, the object is created eagerly. it means whether this method is called or this singleton object is present in the memory.
// It is wasting our memory. (suppose it i huge class no one is asking for its object but still object is created).
// the better solution is we should do lazy initialization.

class CalculatorLazy {
    public int a, b;

    private static CalculatorLazy obj;

    private CalculatorLazy() {}

    public int sum() {
        return a + b;
    }

    public static CalculatorLazy getInstance() {
        if(obj == null) {
            obj = new CalculatorLazy();
        }
        return obj;
    }
}

class SingletonMain {
    public static void main(String[] args) {
        CalculatorEager ce1 = CalculatorEager.getInstance();
        CalculatorEager ce2 = CalculatorEager.getInstance();      // both have same object
        ce1.a = 1;
        ce1.b = 2;
        ce1.a = 10;
        ce2.b = 20;
        System.out.println(ce1.sum());
        System.out.println(ce2.sum());


        CalculatorLazy cl1 = CalculatorLazy.getInstance();
        CalculatorLazy cl2 = CalculatorLazy.getInstance();
        cl1.a = 1;
        cl1.b = 2;
        cl2.a = 15;
        cl2.b = 7;
        System.out.println(cl1.sum());
        System.out.println(cl2.sum());
    }
}

/// -------------- Thread Safe singleton using synchronized ---------------
// for multithreading evn
class ThreadSafeSingleton {
    int a, b;

    private static ThreadSafeSingleton obj;

    private ThreadSafeSingleton() {
        System.out.println("Instance Created");
    }

    public int sum() {
        return a + b;
    }

    public synchronized static ThreadSafeSingleton getInstance() {
        if(obj == null) {
            obj = new ThreadSafeSingleton();
        }
        return obj;
    }
}
class ThreadSafeSingleMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            ThreadSafeSingleton.getInstance();
        });

        Thread t2 = new Thread(() -> {
            ThreadSafeSingleton.getInstance();
        });

        t1.start();
        t2.start();

        // only one instance is created.
    }
}

/// ----------------- double-checked locking mechanism ------------------
// but this synchronized singleton also have drawback.
// [ reason ] With synchronized only one thread can access the method at a time.
//      It is good in the beginning because it will allow only one instance to be created.
//      But once the object has been created now there is no need for a lock. Now even if multiple
//      every thread still has to wait for the lock. Now, this adds unnecessary overhead and can impact the performance,
//      especially in high concurrency applications.
// we need to lock only once when object isn't created. after that locking isn't necessary.
class DoubleCheckSingleton {
    int a, b;

    private static DoubleCheckSingleton obj;

    private DoubleCheckSingleton() {
        System.out.println("Instance Created");
    }

    public int sum() {
        return a + b;
    }

    public static DoubleCheckSingleton getInstance() {
        if(obj == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (obj == null) {
                    obj = new DoubleCheckSingleton();
                }
            }
        }
        return obj;
    }
}
class DoubleCheckedMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            DoubleCheckSingleton.getInstance();
        });

        Thread t2 = new Thread(() -> {
            DoubleCheckSingleton.getInstance();
        });

        t1.start();
        t2.start();
    }
}

// https://www.youtube.com/watch?v=Hanrbuzmv94 (ankur nema)
// https://www.youtube.com/watch?v=zHWusHi9Nt0 (how to break singleton - durgesh tiwari)
/// ----- bill pugh ----

/// ---- enum ----


// real world -
// logging system - a single logger instance writes logs throughout the application.
// database connection manager - only one object manages Db connections across the application.
// configuratio/properties manager - reads application configuration once and shares it everywhere.


// ---- factory design pattern -------