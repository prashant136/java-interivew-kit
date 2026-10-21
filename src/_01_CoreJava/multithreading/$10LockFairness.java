package _01_CoreJava.multithreading;

import java.util.concurrent.locks.*;

public class $10LockFairness {

    final private Lock lock = new ReentrantLock();  // unfair
//    final private Lock lock = new ReentrantLock(true);  /// lock fairness

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquire the lock.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " release the lock.");
            lock.unlock();
        }
    }

    void main() {
        $10LockFairness obj = new $10LockFairness();
        Runnable work = () -> {
            obj.accessResource();
        };

        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);
        Thread t3 = new Thread(work);

        t1.start(); t2.start(); t3.start();
    }
}

/// [ A fair lock give access to threads in the exact order they requested it ]
/// lock fairness - jis order me thread request karega usi order me execute hongi. lock fairness se koi thread starvation nhi hoga. Saare Thread ko access milega (100% sure)
/// multiple threads kis order me execute hongi ye decide OS thread scheduler karta hai.

/// lock unfairness - Risk of thread starvation, doesn't guarantee the execution order. Saare Thread ko access milega ye sure nhi.

/// ----------- difference Synchronization -----------
/// 1. synchronization me fairness nhi hoti.
/// 2. Indefinitely blocking
/// 3. Can't interrupt the thread
/// 4. Doesn't have read / write locking
