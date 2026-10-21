package _01_CoreJava.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance = 1000;

    /// hr object ke pass apna lock hota hai. jo syncronized use krne se active hota hai.
    /// but jab humein fine control chahiye hota hai toh hum Locks use krte hai.
    ///
    /// KUCH Methods hote hai :-
    /// 1. lock.lock() - ye tab tak wait krta hai jab tak lock na mil jaye (chahe doorsa thread lock acquire krke baita ho)
    /// Behavior: It stops execution until it successfully gets the lock.
    /// 2. lock.tryLock() - agar lock available hai toh lock acquire karo wrna bina kuch kiye nikal lo. Ye instant check krta h wait nhi krta.
    /// return type - true (jab lock acquire ho jaye), false (jab lock acquire na kr paye)
    /// 3. lock.tryLock(1500, TimeUnit.MILISECOND) - ye 1500 ms wait karega phir ya lock acquire karega nhi to nikal lega.
    /// 4. lock.unlock() - lock release krta hai. isko finally me execute krte hai.
    final private Lock lock = new ReentrantLock();

//    public synchronized void withdraw(int amount) {
//        System.out.println(Thread.currentThread().getName() + " is trying to withdraw: " + amount);
//        if(amount <= balance) {
//            System.out.println(Thread.currentThread().getName() + " Start withdrawal");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//
//            }
//            balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " After withdrawal, remaining amount " + balance);
//        } else {
//            System.out.println("Insufficient amount");
//        }
//    }

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw: " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (amount <= balance) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Start withdrawal");
                        Thread.sleep(2000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " After withdrawal, remaining amount " + balance);
                    } catch (InterruptedException e) {
                        /// jab koi thread exception de rha hota hai.
                        /// do cheeze kr sakte ho - 1. throw exception  2. interrupt the thread
                        /// or koi clean up kr sakte ho
                        Thread.currentThread().interrupt();     // good practice (manual interrupt krke maine us thread ki state restore kri hai)
                    } finally{
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " Insufficient amount");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock");
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + "could not acquire lock");
        }
        if(Thread.currentThread().isInterrupted()) {
            // ... cleanup task after thread is interrupted (good practice)
        }
    }
}

public class $8ThreadLock {
    void main() throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Runnable task = () -> {
            bankAccount.withdraw(100);
        };
        Thread t1 = new Thread(task, "thread-1");
        Thread t2 = new Thread(task, "thread-2");
        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}
