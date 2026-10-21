
package _01_CoreJava.multithreading;

class ThreadYld extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield();  /// khud tempary pause hoke dusre thread ko jagah deta hai.
        }
    }
}

public class $5ThreadYield {
    void main() {
        ThreadYld t1 = new ThreadYld();
        ThreadYld t2 = new ThreadYld();
        t1.start(); t2.start();
    }
}

// example dekho -
// IMP - https://medium.com/@AlexanderObregon/javas-thread-interrupt-method-explained-0df0f8f6a428
//public class InterruptFlagCheckExample implements Runnable {
//    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
//            System.out.println("Thread is running.");
//            try {
//                Thread.sleep(1000);  // Simulate some work
//            } catch (InterruptedException e) {
//                // Re-interrupt the thread to preserve the interrupted status
//                Thread.currentThread().interrupt();
//                System.out.println("Thread was interrupted during sleep.");
//            }
//        }
//        System.out.println("Thread has stopped running.");
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new InterruptFlagCheckExample());
//        thread.start();
//
//        Thread.sleep(3000);  // Main thread sleeps for 3 seconds
//        thread.interrupt();  // Interrupt the running thread
//    }
//}

/// temporarily pause the execution of the currently running thread.
/// It serves as a hint to the thread scheduler that the current thread is willing to give up its give remaining CPU
/// to other threads (they can execute higher priority taks)