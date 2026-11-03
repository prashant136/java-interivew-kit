package _01_CoreJava;

/// --------------------------------- CompletableFuture -----------------------------------

/// introduced in java 8,


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * we have some limitations in Future that is Overcome by CompletableFuture -
 * 1. Future doesn't have Callback Function
 * 2. Multiple Futures can't be combined.
 * 3. Multiple Futures can't be chained together.
 * 4. Pool Exception handling
 */

/**
 * CompletableFuture gets its threads from the forkJoinPool.commonPool() by default.
 *  ????? CompletableFuture ko executor service ke sath kaise use kare aur kyu ???????
 */

/// [definition] CompletableFuture is a class introduced in (Java 8) used for asynchronous, non-blocking programming. It is used to handle complex multithreading task by chaining task, combining multiple parallel operations, handling error gracefully without locking the main thead gracefully.
/// Limitation of Future and advantage of CompletableFuture -
/// 1. Blocking the thread - The only way to get a value from a Future is by calling future.get(), which blocks the calling thread until the computation finishes.
///     in completable future we have non-blocking method thenApply(), thenAccept() to complete the task without even blocking the current thread.
/// 2. You cannot chain the callback in Future.
/// 3. We can't combine the multiple future. future has no way to run multiple future in parallel, combine their results. in CompletableFuture we have thenCombine() to combine multiple future.
/// 4. No built-in exception in future. we use ExecutionException manually to catch exception. in CompletableFuture we have exceptionally() method to handle exception gracefully.



// here we sre doing 3 process separately (preform some task, retrieve something, we will do some work with value we retrieve)
// all these task can club together in single pipeline, that can be done by callback
class Limitation1 {
    void main() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> {      // preform some task
            System.out.println("Fetching the name");
            return "Prashant";
        });
        String name = future.get();     // retrieve something
        System.out.println("sending welcome email to: " + name);    // do some work with value we retrieve
        executorService.shutdown();
    }
}
class Solution1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello World");
            return "Hello World";
        }).thenAccept(name ->
                System.out.println("sending welcome email to: " + name)
        );
    }
}

// in this example, I have taken 2 different futures. These things are done in detached way.
// with CompletableFuture, we just combine two futures to gather the perform some task
class Limitation2 {
    void main() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println("Fetching mutual funds balance");
            return 50000;
        });
        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println("Fetching FD balance");
            return 100000;
        });

        Integer mf = future1.get();
        Integer fd = future2.get();
        System.out.println("Total balance: " + (mf + fd));

        executorService.shutdown();
    }
}
class Solution2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching mutual funds balance");
            return 50000;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching FD balance");
            return 100000;
        });

        future1.thenCombine(future2, (x, y) -> x + y)
                .thenAccept(res -> System.out.println("Total balance:" + res));

    }
}


class Limitation3 {
    void main() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> priceFuture = executorService.submit(() -> getProductPrice());
        int price = priceFuture.get();

        Future<Integer> finalPriceFuture = executorService.submit(() -> applyDiscount(price));
        int finalPrice = finalPriceFuture.get();

        System.out.println("Final Price: " + finalPrice);

        executorService.shutdown();
    }

    int getProductPrice() {
        System.out.println("Fetching price");
        return 1000;
    }

    int applyDiscount(int price) {
        System.out.println("Applying discount");
        return price - 200;
    }
}
class Solution3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(() -> getProductPrice())
                .thenApply(price -> applyDiscount(price))
                .thenAccept(finalPrice -> System.out.println("finalPrice: " + finalPrice));


    }
    static int getProductPrice() {
        System.out.println("Fetching price");
        return 1000;
    }

    static int applyDiscount(int price) {
        System.out.println("Applying discount");
        return price - 200;
    }
}

// in executorService when we throw exception, it will not throw right way. we will get the exception when we use get() method
class Limitation4 {
    void main() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> PaymentFuture = executorService.submit(() -> {
            System.out.println("Making payment");
            if(true) {
                throw new RuntimeException("Payment failed");
            }
            return "Payment Successful";
        });

        try {
            String result = PaymentFuture.get();
            System.out.println("Payment: " + result);
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }

        executorService.shutdown();
    }

    int getProductPrice() {
        System.out.println("Fetching price");
        return 1000;
    }

    int applyDiscount(int price) {
        System.out.println("Applying discount");
        return price - 200;
    }
}
class Solution4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Making payment");
            if (true) {
                throw new RuntimeException("Payment failed");
            }
            return "Payment Successful";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "Payment Failed";
        });
        System.out.println(future.get());
    }
}

// SupplyAsyc() - execute a task asynchronously. (similar to submit method), It returns CompletableFuture<T>. it can also take executor service.
// thenAccept() - takes the result of the previous stage and performs an action with it, but returns CompletableFuture<Void>.
// thenApply() - takes the result of the previous task and execute an action then return the transformed result. return type CompletableFuture<T>.
// thenCombine() - method is used to merge the results of two independent/parallel asynchronous tasks.
// exceptionally() - exceptionally() acts like a catch block. In any stage before if throws an exception, the execution skips normal steps and jumps straight into exceptionally().





/// ----------------------------- Volatile Keyword --------------------------------
/// Imagine we have two threads working with the same variable. Suppose the initial value of that variable is 10.
/// Now thread 1 changes that value from 10 to 20. We would naturally expect thread 2 to see 20. Right?
/// But Java does not guarantee that thread 2 will immediately see the updated value.
///
///  A computer can have one CPU with multiple cores. Thread one could be running on core one while thread 2 is running on core two.
/// CPU has a main memory or RAM, but each core has its own cache memory for faster processing. Now, when thread 1 updates the variable,
/// that update initially happens in core 1's cache. The processor may take a while to update the same in main memory. It generally happens
/// multiple update requests and then apply these requests to the main memory all at once. That's why the update is not
/// immediately visible to thread 2.
///
///  This problem is solved by volatile keyword. When we declare a variable as volatile,
/// we are essentially telling the JVM this variable is shared between multiple threads. So don't let a thread keep
/// using a stale cache copy. So if thread one updates the value from 10 to 20, it's immediately updated to the RAM.
/// Also, RAM notifies all the threads that this value has been updated. So their cache value is no longer the source of
/// truth. Now when thread 2 reads that volatile variable, it will see the most recently written value inside the RAM.

/**
    Before volatile -

         [ CORE 1 ]                      [ CORE 2 ]
         Thread 1                        Thread 2
            │                               │
            ▼                               ▼
         ┌──────────┐                    ┌──────────┐
         │ Cache    │                    │ Cache    │
         │  a = 20  │                    │  a = 10  │
         └────┬─────┘                    └──────────┘
              │
     🕒 Takes Time / Bulk Writes
              ▼
         ┌──────────────────────────────────────────────┐
         │             RAM (MAIN MEMORY)                │
         └──────────────────────────────────────────────┘

    After volatile -

         [ CORE 1 ]                      [ CORE 2 ]
         Thread 1                        Thread 2
            │                               │
            ▼                               ▼
         ┌──────────┐                    ┌──────────┐
         │ Cache    │                    │ Cache    │
         │  a = 20  │                    │ a=10 ❌  │
         └────┬─────┘                    └────▲─────┘
              │                               │
         ⚡️ Immediate Update             📖 Reads 20 from RAM
              ▼                               │
         ┌───────────────────────────────────────┴──────┐
         │             RAM (MAIN MEMORY)                │
         └──────────────────────────────────────────────┘
 */

/// Variable is shared b/w multiple threads, so don't let a thread keep using a stale cached copy.
/// yaha ek issue thread t2  [ running = false aur count = 1 ] kr de but jo thread t1 me immediate reflect na ho. thoda time lage. (visibility issue)
/// ek dursa issue reordering issue. running aur count update ho gya but dono alag-alag time pe reflect ho instead of reflecting at same time.

class WithoutVolatileKeyword {
    boolean running = true;
    int count = 0;

    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(running) {
                System.out.println("t1 is running");
            }
            System.out.println("count:" + count);
        });

        Thread t2 = new Thread(() -> {
            count = 1;
            running = false;
            System.out.println("t1 has finished");
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}

class VolatileKeyword {
    volatile boolean running = true;
    volatile int count = 0;

    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(running) {
                System.out.println("t1 is running");
            }
            System.out.println("count:" + count);
        });

        Thread t2 = new Thread(() -> {
            count = 1;
            running = false;
            System.out.println("t1 has finished");
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}

// concurrent hashmap internal working ?
// why concurrent hashmap vs synchronizd map ?
// hashtable va hashmap ?
// compare swap algo ?

/// --------------------- Atomic integer ----------------------
// Q:) Atomic Operations (Atomicity) - In a multithreaded environment, an atomic operation cannot be interrupted midway by another thread;
//  it either completes entirely or does not happen at all. It is ensuring thread-safe operations and preventing race conditions.
//  It uses Compare and Swap Algo.
// example - concurrentHashmap, AtomicInteger.
// 🚩 humein volatile keyword use krne ki need nhi hai. jab hum AtomicInteger jaise use kr rahe hai.

// AtomicInteger methods -
// 1. get() - get the value
// 2. set(value) - set value to atomicInteger
// 3. incrementAndGet() - increment karo phir get {++x)
// 4. getAndIncrement() - get karo phir increment (x++)
// 5. decrementAndGet()
// 6. GetAndDecrement() -
// 7. AddAndGet(value) - ad kro phir get
// similarly we have AtomicLong, AtomicDouble, AtomicBoolean


/// Q:) What is difference between AtomInteger and synchronized and Volatile ?
// synchronized - synchronized keyword directly relies on object-level locks (also known as intrinsic locks or monitors).
//      When a thread enters a synchronized block, it acquires a monitor lock. Other threads are in waiting state until the lock is released.
//      synchronized involved thread context switching and thread blocking overhead, which can degrade performance. { It guarantees both Visibility + Atomicity }
// AtomicInteger - unlike synchronization AtomicInteger don't block the entire object. It uses Compare and swap (CAS) Algo to update the value. AtomicInteger is designed specifically for atomic operations on a single variable (like incrementing, decrementing, or updating a counter)
//      it avoids thread blocking, that makes significantly faster. { It ensures both Visibility + Atomicity }
// Volatile - It resolve visibility problem. when we declare a variable as volatile, it forces all reads and writes to go directly to Main Memory (RAM) rather than relying on thread-local CPU caches.
//      this ensures that any update by one thread is instantly visible to all other threads. It doesn't block thread { It only ensure Visibility }

class Counting {

/// --- ye to synchronization hai jisme pura object lock ho jata hai. but atomic operations different hai (Lock free concurrency)
//    int count = 0;
//    public synchronized void increment() {
//        count++;
//    }

// ---- AtomicInteger ----
    AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.incrementAndGet();    // 1
    }
}
class AtomicIntegerDemo {
    void main() throws InterruptedException {
        Counting c = new Counting();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.increment();
            }
        });
        t1.start();
        t2.start();

        Thread.sleep(1000);

        t1.join();
        t2.join();

        System.out.println(c.count);
    }
}