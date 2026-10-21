package _01_CoreJava.multithreading;

import java.util.concurrent.locks.*;

public class $9ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("outer method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("inner method");
        } finally {
            lock.unlock();
        }
    }
    void main() {
        $9ReentrantExample obj = new $9ReentrantExample();
        obj.outerMethod();
    }
}

/// ReentrantLock ka naam hi "Reentrant" isliye hai kyunki 
/// ek thread (SAME THREAD) same lock ko multiple times (bina deadlock) acquire kar sakta hai.
/// Jitni baar lock() call hota hai, lock ka internal counter utna hi badhta hai, aur utni hi baar unlock() call karna padta taaki lock completely release ho sake.