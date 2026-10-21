package _01_CoreJava.multithreading;

class ThreadPr extends Thread {
    public ThreadPr(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Priority: " + Thread.currentThread().getPriority() + " count: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/// No Guarantee of Execution Order: Setting a thread priority serves as a hint to the operating system's thread scheduler.
/// It does not guarantee that a higher-priority thread will always finish first or run before a lower-priority one.

/// We can use a custom priority number, but it must be an integer between [1, 10] inclusive.
/// t1.setPriority(12); // ❌ Throws IllegalArgumentException at runtime
public class $3ThreadPriority {
    void main() {
        ThreadPr l = new ThreadPr("low priority thread");
        ThreadPr m = new ThreadPr("medium priority thread");
        ThreadPr h = new ThreadPr("high priority thread");
        l.setPriority(Thread.MIN_PRIORITY);    // 1
        m.setPriority(Thread.NORM_PRIORITY);   // 5 - default
        h.setPriority(Thread.MAX_PRIORITY);    // 10
        l.start();
        m.start();
        h.start();
    }
}
