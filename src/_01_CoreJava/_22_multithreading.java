package _01_CoreJava;

import java.util.concurrent.*;

/// ----------------------------------- Synchronized Keyword -----------------------------------
/// Multithreaded environment - multithreaded environment allows you to process several tasks at the time rather than performing them one by one.
///
/// [definition] The Synchronized keyword ensure thread safety where multiple threads run in parallel. It helps prevent race condition. It ensures that one thread can access critical section at a time.
/// Synchronized ensures that one thread at a time can execute a synchronized block or method.
/// critical section - Shared resource
/// [definition] race condition in Java occurs when two or more threads concurrently access and modify a shared resource without proper synchronization.
class Counter1 {
    int count = 0;
//    void increment() {        // not thread safe
//        count++;
//    }

    // thread safe
    synchronized void increment() {
        count++;
    }

}
class SynchronizedMain {
    public static void main(String[] args) throws InterruptedException {
        Counter1 counter = new Counter1();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count will be: " + counter.count);
    }
}

/// Deadlock - It is situation where two or more thread are permanently blocked because each thread is waiting for other thread to release a lock.
///
///
/// [Not a deadlock] flow of example 1 -
/// these are two thread is finished their execution, or they still are in BLOCKED state
/// as soon as thread t1 start, it takes a lock on account1 object (as long as thread t1 is executing on account1 object, no other thread can access account1)
/// thread t2 takes lock on account2 object.
/// both thread t1, t2 does not depend on each other, so this isn't deadlock condition.
class BankAccount {
    public void changeBalance() {
        System.out.println("Changing balance");
    }
}
class NoDeadlockMain1 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        Thread t1 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 1 has taken a lock");
                account1.changeBalance();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (account2) {
                System.out.println("Thread 2 has taken a lock");
                account2.changeBalance();
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        t1.join();
        t2.join();
    }
}

/// [No deadlock example] example 2 -
/// as soon as t1 start, it takes a lock on account1 then it tries to take a lock on account2. but Thread t2 has already taken lock on account2,
/// unless thread t2 finishes it execution, t1 can't acquire the lock on account2, so thread t1 will wait.
class NoDeadlockMain2 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        Thread t1 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 1 has taken a lock");
                account1.changeBalance();

                synchronized (account2) {       // Thread t1 waits here till t2 finish its execution and release the lock.
                    System.out.println("Thread 2 has taken a lock ::");
                    account2.changeBalance();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (account2) {
                System.out.println("Thread 2 has taken a lock");
                account2.changeBalance();

//                synchronized (account1) {
//                    System.out.println("Thread 1 has taken a lock");
//                    account1.changeBalance();
//                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        t1.join();
        t2.join();
    }
}

/// [ DEADLOCK EXAMPLE ] - both thread are waiting for each other to finish their execution. Both of them cannot release their locks. Both of them cannot finish
///     their execution. And until one of them finishes the execution, So, both of them are in a deadlock situation.
class DeadlockMain {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        Thread t1 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 1 has taken a lock on account 1");
                account1.changeBalance();

                synchronized (account2) {       // Thread t1 waits here till t2 finish its execution and release the lock.
                    System.out.println("Thread 2 has taken a lock on account 2");
                    account2.changeBalance();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (account2) {
                System.out.println("Thread 2 has taken a lock on account 2");
                account2.changeBalance();

                synchronized (account1) {   // Thread t2 waits here till t1 finish its execution and release the lock.
                    System.out.println("Thread 1 has taken a lock on account 1");
                    account1.changeBalance();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        t1.join();
        t2.join();
    }
}

/// prevention - don't write this type of code (where both are depended on each other) change the order
/// suppose thread take lock on account1 then account2. in this way thread t2 wait till thread t1 will release lock on both account1, account2. then thread t2 start.
class DeadlockPrevention {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        Thread t1 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 1 has taken a lock on account 1");
                account1.changeBalance();

                synchronized (account2) {
                    System.out.println("Thread 1 has taken a lock on account 2");
                    account2.changeBalance();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 2 has taken a lock on account 1");
                account1.changeBalance();

                synchronized (account2) {
                    System.out.println("Thread 2 has taken a lock on account 2");
                    account2.changeBalance();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        t1.join();
        t2.join();
    }
}

/// --------------------------- RUNNABLE AND CALLABLE ----------------------------
/// both runnable and callable are functional interfaces.
class MyTask1 implements Runnable {
    public void print() {
        System.out.println("MyTask1 print: " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}
class MyTask2 implements Callable<Integer> {
    public int getValue() {
        System.out.println("Fetching value: " + Thread.currentThread().getName());
        return 100;
    }

    @Override
    public Integer call() throws Exception {
        return getValue();
    }
}
class RunnableCallableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

/// 1. ---------------- a lot of boilerplate code here ----------------

//        MyTask1 runnableTask = new MyTask1();
//        MyTask2 callableTask = new MyTask2();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(runnableTask);
//        executorService.submit(callableTask);


/// 2. --------------------- use lambda (shortcut) ----------------------

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Runnable runnable = () -> {
//            System.out.println("MyTask1 print: " + Thread.currentThread().getName());
//        };
//        Callable callable = () -> {
//            System.out.println("Fetching value: " + Thread.currentThread().getName());
//            return 100;
//        };
//        executorService.submit(runnable);
//        executorService.submit(callable);


// 3. most optimized - even thought we can directly write runnable and callable like this.
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            System.out.println("MyTask1 print: " + Thread.currentThread().getName());
        });
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Fetching value: " + Thread.currentThread().getName());
            return 100;
        });
        System.out.println(future.get());
    }
}
/// Runnable - run() does not return (Runnable is used to execute an async task without any result)
/// Callable - call() return some value (Callable is used to execute an async task which returns some result)
///   1. call() method return value which is a type Future<T>
///   2. Future :: Future ia an interface which holds the result of task. To get value form Future -> just do future.get() method
///
/// [definition] Both runnable and callable is used to run task using thread. Runnable has run() method for code execution but doesn't return anything.
///     while callable has call() method which returns Future. (future is interface that hold the result of async task). future.get() used for get the value from future.


/// ------------------------------------[ IMPORTANT ] ExecutorService ------------------------------------
/// Q:) Executor and ExecutorService me difference ?
///     Executor - Executor is an interface that consists of just one method: execute(Runnable command).
///             execute() method is executing asynchronous task. It takes a Runnable object as params.
///     ExecutorService - ExecutorService is sub-interface of Executor. It provides several methods to manage and control the execution of threads.
///             it has several method like shutdown, submit, shutdownNow(), invokeAny(), invokeAll(), isTerminated() etc.
///
/// Q:)  difference b/w submit and execute ??
///     execute() - used for executing asynchronous task. execute() method peasant in Executor interface. (take Runnable interface as param) and return noting.
///     submit() - used for executing asynchronous task. submit() method present in ExecutorService interface. it takes both Runnable and Callable and return Future object.
///         (The Future object is used to get the task result or to check if the task has finished executing.)
///
//      execute() - Runnable as params, return - void
//      submit() with Runnable - Runnable as params, return - Future<?>.
//      submit() with Callable - Callable as params, Return - Future<T>.
///
/// Q:)  ExecutorService.shutdown() vs shutdownNow() ??
///     shutdown() -
///
///     shutdownNow() -
///
/// Q:) Type of Executor ??
///
/// ✅ [definition] ExecutorService is an interface which we use to create and manage a pool of threads. Instead of manually creating and managing Thread objects, ExecutorService automatically
///     managing the thread pool. So that we can resue the thread because creating and destroying threads is expensive operation.
///
/// Question:: Why do we need ExecutorService even if we have Thread class or Runnable ?
/// Ans:: We just keep on creating thread everytime we need using thread class. threads take memory, so we can't make infinite number of thread.
///     thread are also limited to JVM. we need to reuse thread (instead of creating and destroying thread). In java, we have thread pool for reusing.
///     thread objects will be difficult to maintain.
///
class ExecutorServiceMain1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);      // we have 3 threads to perform the task

        // we have 3 threads that is executing the task (basically we are reusing the thread)
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                System.out.println("Task1 executing: " + Thread.currentThread().getName());
            });
        }

        // main thread is executing
        System.out.println("Main method continues: " + Thread.currentThread().getName());

        executorService.shutdown();
    }
}

/// Yaha main thread execute ho rha hai. aur tino threads uske execute ho rha hi separately.
///
/// execute() takes a runnable and doesn't return anything, so we can't directly track whether the task has completed or wait for its execution.
///     execute method se task is execute asynchronously, but we have no way to whether task is completed or no way to wait for task.
/// Isilye es example me hum track nhi rakh pa rhe hai, because execute() method ka koi return type nhi h. main thread aur baki thread execute ho rhe hai
///
/// submit() accepts both runnable, callable and has return type of Future<T>. it holds information.
/// future.get() - get() method waits for the task to complete
class ExecutorServiceMain2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);      // we have 3 threads to perform the task

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Email send");
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("SMS send");
        });
        
        System.out.println("All Notification sent successfully: " + Thread.currentThread().getName());

        executorService.shutdown();
    }
}


/// isDone() tells whether task has completed or not.
/// cancel() attempts to cancel the task before it completes. (interrupt before complete)
class ExecutorServiceMain3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);      // we have 3 threads to perform the task

        // takes Runnable
        Future<?> future1 = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Email send");
        });

        // takes Runnable
        Future<?> future2 = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("SMS send");
        });

        System.out.println("is task1 done: " + future1.isDone());

        // all these task run in parallel in diff. threads, the main thread waits until its completion. after completion the main thread run further
        // Yaha main thread wait karega jab tak information get nhi ho jati, phir aage badhega. pahle ye future1, future2 se value get hoga then main thread aage badhega.
        // task will be done after get().
        future1.get();
        future2.get();

        System.out.println("is task1 done: " + future1.isDone());

        System.out.println("All Notification sent successfully: " + Thread.currentThread().getName());

        executorService.shutdown();
    }
}


/// 3 thread hai aur tino thread kuch value return kr rhi hai.
class ExecutorServiceMain4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);      // we have 3 threads to perform the task

        // takes Callable
        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println("Fetching Account balance");
            return 30000;
        });

        // takes Callable
        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println("Fetching Mutual Funds balance");
            return 50000;
        });

        // takes Callable
        Future<Double> future3 = executorService.submit(() -> {
            System.out.println("FD balance");
            return 40000.00;
        });

        Integer balance1 = future1.get();
        Integer balance2 = future2.get();
        Double balance3 = future3.get();

        System.out.println(balance1 + balance2 + balance3);

        executorService.shutdown();  /// thread ko shutdown kia jata , otherwise JVM think these thread are alive and can use later.
    }
}


// ---------------- print odd even number using 2 threads ------------------------
class OddEvenPrinter {
    private int current = 1;
    private final int MAX = 10;

    public synchronized void printOdd() {
        while (current <= MAX) {
            // Agar number even hai, toh odd thread wait karega
            if (current % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Odd Thread: " + current);
                current++;
                notify(); // Even thread ko jagayega
            }
        }
    }

    public synchronized void printEven() {
        while (current <= MAX) {
            // Agar number odd hai, toh even thread wait karega
            if (current % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Even Thread: " + current);
                current++;
                notify(); // Odd thread ko jagayega
            }
        }
    }
}
class OddEvenTest {
    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinter printer = new OddEvenPrinter();

/// ------------ using two threads ---------
//        Thread t1 = new Thread(() -> printer.printOdd(), "Thread-Odd");
//        Thread t2 = new Thread(() -> printer.printEven(), "Thread-Even");
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

/// ----------- using ExecutorService -------------
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> printer.printOdd());
        executor.submit(() -> printer.printEven());
        executor.shutdown();
    }
}

// -------------- print sum of Arrays ------------
class ArraySumTask implements Callable<Integer> {
    private int[] array;
    private int start;
    private int end;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum; // Yeh thread apna sum return karega
    }
}
class CallableArraySumExample {
    void main() throws InterruptedException, ExecutionException {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executor.submit(new ArraySumTask(numbers, 0, 5));
        Future<Integer> future2 = executor.submit(new ArraySumTask(numbers, 5, 10));

        // future1.get() aur future2.get() threads ka result collect karte hain
        int totalSum = future1.get() + future2.get();

        System.out.println("First half sum: " + future1.get());   // Output: 15
        System.out.println("Second half sum: " + future2.get());  // Output: 40
        System.out.println("Total Sum of Array: " + totalSum);    // Output: 55

        // Executor band kar diya
        executor.shutdown();
    }
}


// introduction me thoda structure karo. (tech stack ek jagah backend, fornend) phir tyroo me vidtech, freelanching ka bhi bolo.;

// Runnable, callable

// thread class, runnable interface me difference (memory optimizatio)

// CompitableFuture - ??? methods ke naam kya krti hai bs

// ExecutorService ??? -- thode theory based dekh lio.


