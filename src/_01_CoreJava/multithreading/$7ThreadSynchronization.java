package _01_CoreJava.multithreading;

class Counter {
    private int count = 0;

    synchronized void increment() {
        count++;    /// jha shared resource access / modify kiye jate hai = critical section
    }

//    void increment() {
//        synchronized (this) {     /// yaha this ka matlab hai particular us object me.
//            count++;
//        }
//    }

    void print() {
        System.out.println(count);
    }
}

class ThreadSyn extends Thread {
    private Counter counter;
    public ThreadSyn(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

public class $7ThreadSynchronization {
    void main() throws InterruptedException {
        Counter counter = new Counter();
        ThreadSyn t1 = new ThreadSyn(counter);
        ThreadSyn t2 = new ThreadSyn(counter);
        t1.start(); t2.start();
        t1.join(); t2.join();
        counter.print();
    }
}
