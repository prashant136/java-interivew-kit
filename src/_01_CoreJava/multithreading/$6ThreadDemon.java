package _01_CoreJava.multithreading;

class ThreadDm implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("hello world");
        }
    }
}

public class $6ThreadDemon {
    void main() {
        Thread t = new Thread(new ThreadDm());
        t.setDaemon(true);      // now t is demon thread
        t.start();
        System.out.println("main done");
    }
}

/// daemon thread is a background thread in Java that runs to support user (non-daemon) threads
/// automatically terminates when all user threads finish execution. JVM doesn't wait for demon thread to finish it task.
/// agar JVM user thread ke liye wait karega but demon thread ke liye nhi.