package _01_CoreJava;

/// --------------------------------- CompletableFuture -----------------------------------

/// introduced in java 8,
// ExecutorService kya hai? (The Thread Pool Manager)
// CompletableFuture kya hai? (The Result & Pipeline Handler)


import java.util.concurrent.*;

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
 *
 * SupplyAsyc() - execute a task asynchronously. (similar to submit method), It returns CompletableFuture<T>
 *  thenAccept() - consumes the result of the previous task and execute an action after it completes.
 *  thenAccept() - is used when we want to take an input but don't return any output.
 *  thenApply() - is used when we want to take an input as well as return some output.
 *  exceptionally() -
 *
 */



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

// in executorService when we throw exception, it will npt throw right way. we will get the exception when we use get() method
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

class WithoutVolatileKeyword {
    boolean running = true;

    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(running) {
                System.out.println("t1 is running");
            }
        });

        Thread t2 = new Thread(() -> {
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

    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(running) {
                System.out.println("t1 is running");
            }
        });

        Thread t2 = new Thread(() -> {
            running = false;
            System.out.println("t1 has finished");
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}