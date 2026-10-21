package _01_CoreJava.multithreading;


/// In Java Threads, if any thread is in sleeping or waiting state (i.e. sleep() or wait() is invoked),
/// calling the interrupt() method on the thread, breaks out the sleeping or waiting state throwing InterruptedException.
/// If the thread is not in the sleeping or waiting state, calling the interrupt()
/// method performs normal behavior and doesn't interrupt the thread but sets the interrupt flag to true.

class Thread1 extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("thread is running");
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException occur" + e);
        }
    }
}


/// In the program, there is no exception occurred during the execution of the thread.
/// Here, interrupt only sets the interrupted flag to true, which can be used by Java programmers later.
class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " :: is interrupted");
                break;
            }
            System.out.println(i);  // 0,1,2,3,4
        }

    }
}


public class $4ThreadInterrpt {
    void main() {
//        Thread1 t1 = new Thread1();
//        t1.start();
//        t1.interrupt();
//        System.out.println("Main thread execution completes");


        Thread2 t2 = new Thread2();
        t2.start();
        t2.interrupt();
        System.out.println("Main thread execution completes");
    }

}
