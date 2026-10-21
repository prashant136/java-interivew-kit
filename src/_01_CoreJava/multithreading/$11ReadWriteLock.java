package _01_CoreJava.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class $11ReadWriteLock {

    private int count = 0;
    final private ReadWriteLock lock = new ReentrantReadWriteLock();
    final private Lock readLock = lock.readLock();
    final private Lock writeLock = lock.writeLock();

    /// when writeLock is acquired, no threads can access (can't read/write) at the same time.
    public void increment() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    ///  multiple threads can acquire this readLock only when writeLock isn't acquired yet.
    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }


    void main() throws InterruptedException {
        $11ReadWriteLock obj = new $11ReadWriteLock();

        Runnable read = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " reads " + obj.getCount());
            }
        };

        Runnable write = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " writes");
                obj.increment();
            }
        };

        Thread writeTh = new Thread(write);
        Thread readTh1 = new Thread(read);
        Thread readTh2 = new Thread(read);

        writeTh.start(); readTh1.start(); readTh2.start();

        writeTh.join(); readTh1.join(); readTh2.join();

        System.out.println("final output: " + obj.getCount());
    }
}

/// ReentrantLock ya synchronized mein ek waqt par sirf ek hi thread ko access milta hai. chahe thread data ko sirf read kar raha ho ya update.
/// Lekin ReadWriteLock do alag-alag locks provide karta hai:-
/// 1. Read Lock (lock.readLock()): jab threads ko sirf data ko read karna toh Read Lock use karega. Multiple threads parallel mein read kar sakte hain only when (koi thread write lock acquire na kare.)
/// 2. Write Lock (lock.writeLock()):  jab thread data modify krna chahe toh Write Lock use karega. Jab write lock acquired ho, toh dusra thread read/write nhi kar sakta kar sakta.