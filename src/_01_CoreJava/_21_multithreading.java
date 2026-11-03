package _01_CoreJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// we have 3 class task1, task2, task3. they are run sequentially. I want to execute these task in parallelly, using multithreading
class Task1 extends Thread {
    void print() {
        System.out.println("Executing task 1 " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}
class Task2 extends Thread {
    void print() {
        System.out.println("Executing task 2 " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}
class Task3 extends Thread {
    void print() {
        System.out.println("Executing task 3 " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}
class MultithreadingMain1 {
    void main() {
// ------ main thread is always executing sequentially ------
        Task1 t1 = new Task1();
        Task2 t2 = new Task2();
        Task3 t3 = new Task3();

// ------ since all three thread are running independently, their execution can overlap. So, the output may appear in different order each time.
        t1.start();
        t2.start();
        t3.start();

// ------ if we execute run() directly, no new thread is created, hence main thread is executing.
//        t1.run();
//        t2.run();
//        t3.run();
    }
}
// https://www.youtube.com/shorts/1YBzRy70DcE [for definition]
/// How to create thread in Java -  a. extend Thread class  b. implement Runnable interface
/// 1. start() - it registers a new thread and that new thread internally invokes run() method.
/// 2. run() - it contains the code to be executed.
/// 2. Thread.currentThread() is static method - fetch the thread that is currently executing the code.
/// 3. Thread.currentThread().getName() - return the name of the thread.
///
/// Question:: why do we need to execute start() method, why not run() method directly, [ means why t1.start(); instead of t1.run(); ] ??
/// Ans:: start() creates a new thread and then calls run(), if we calls run() directly, no new thread is created.
///
/// Question:: why introduced Runnable interface if already Thread class is exits ??
/// Ans:: Since java doesn't support multiple inheritance, once we extend a class using Thread, we can't extend further.
///
/// actually run() is a part of Runnable interface not Thread class (why can we still use run() in Thread class if it isn't part of because Thread class also implement Runnable interface)
///
/// Question:: How to execute thread using runnable interface ??
/// Runnable has run() method, but it doesn't have start() method.
/// So first we need to create Task object and Thread object. Pass the Task object to Thread obj as constructor to link
/// (Thread can accept Runnable interface as constructor), then call start().

class Task_1 implements Runnable {
    void print() {
        System.out.println("Executing task_1 " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}
class Task_2 implements Runnable {
    void print() {
        System.out.println("Executing task_2 " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        print();
    }
}

class MultithreadingMain2 {
    void main() {
        // 1. first create task object
        Task_1 task1 = new Task_1();  // these are runnable now.
        Task_2 task2 = new Task_2();

        // 2. create thread object and link
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        // 3. run start()
        t1.start();
        t2.start();

        // we can reduce boilerplate using lambda and method references.
        // no need to do all these stuff. we always do in shorter way using lambda
        // shortcut of - Runnable interface using a lambda expression.
        Thread t3 = new Thread(() -> {
            System.out.println("Executing task_3 " + Thread.currentThread().getName());
        });
        Thread t4 = new Thread(() -> {
            System.out.println("Executing task_4 " + Thread.currentThread().getName());
        });
        t3.start();
        t4.start();
    }
}


/// ---------------------------- Some Important Methods ----------------------------
/// join() - is used to make the current thread wait until another thread finishes its execution.
class MultithreadingMethodMain {
    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Task 1 " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Task 2 " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            System.out.println("Task 3 " + Thread.currentThread().getName());
        });

        t1.start();
        t2.start();
        t3.start();

        // until t1, t2 and t3 finishes main thread must wait.
        t1.join();
        t2.join();
        t3.join();

        System.out.println("Main thread continues...");
    }
}


// -----------------------------------------------------------------------------------------
// Question: Print number from 1 to 30 in order. Odd number and even number should be printed in two different threads.
class MultiThreadingQuestion {
    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 29; i+=2) {
                System.out.println(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 2; i <= 30; i+=2) {
                System.out.println(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();
    }
}


/// -------------------------------- Thread lifecycle --------------------------------
/// Thread.currentThread().getState() - return the current state of the thread.
///
/// Thread lifecycle describes the different states a thread from its creation to its execution completes
/// NEW - The thread instance is created using the new keyword, it has not been started yet.
/// RUNNABLE - The start() method is invoked. The thread is running or the thread is ready to run and wait for CPU to allocate resource.
/// TIMED_WAITING - Thread is waiting for a specified amount of time(Thread.sleep()). It returns to the runnable state when the time expires.
/// TERMINATED - The thread has finished executing its run() method or stopped due to exception. A terminated thread cannot be restarted.
/// WAITING - Thread is waiting indefinity for another thread to perform an action.
/// BLOCKED - Thread is waiting to acquire a lock that is currently held by another thread.
///
///
class ThreadLifeCycle {
    void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Task 1 is executing...");
        });
        System.out.println("t1 state: " + t1.getState());      // Thread is created but not in running state. To run thread need to call t1.start()


        Thread t2 = new Thread(() -> {
            while(true) {

            }
        });
        t2.start();
        Thread.sleep(1000);
        System.out.println("t2 state: " + t2.getState());


        // flow -
        // we created a thread t2.
        // we start thread t2 this thread t2 waits for 5 seconds goes to 'TIMED_WAITING' state
        // main thread goes to sleep for 1 sec
        // after 5 second, thread t2 again goes to 'RUNNABLE' state
        // print state of thread t2 and thread execution finished
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t3.start();
        Thread.sleep(1000);
        System.out.println("t3 state: " + t3.getState());


        Thread t4 = new Thread(() -> {
            System.out.println("Task 4 is executed");
        });
        t4.start();
        Thread.sleep(1000);
        System.out.println("t4 state: " + t4.getState());

    }
}
/// whenever 2, 3 tries to call this method, they will have to call this method one by one. if one thread is executing then second wait till the first thread is completed their execution.
/// the time other thread is waiting, that is the point thread is in blocked state.
/// ------------ BLOCKED state -------------
class Counter {
    int count = 0;

    synchronized void increment() throws InterruptedException {
        count++;
        Thread.sleep(5000);
    }
}
class BlockedThread {
    void main() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            try {
                counter.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                counter.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

        Thread.sleep(1000);

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());   // t2 is in blocked state till t1 will finish its execution.

        t1.join();
        t2.join();
    }
}

/// ---------------------- demon thread ---------------------------
/// [definition] A Daemon Thread is low-priority background thread that runs in the background to perform supporting tasks.
/// As soon as all user (non-daemon) threads finish execution, JVM does not wait for demon thread to finish, JVM terminates and kills running daemon threads.
/// how to enable - thread.setDaemon(true)
class DaemonTask implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("--> [Daemon Thread] Background saving/monitoring data...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
class UserTask implements Runnable {
    private String name;
    private int workTime;

    UserTask(String name, int workTime) {
        this.name = name;
        this.workTime = workTime;
    }

    @Override
    public void run() {
        System.out.println(name + " has started working.");
        try {
            Thread.sleep(workTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(name + " has finished working.");
    }
}
class DaemonDemo {
    void main() throws InterruptedException {
        // Creating 1 Daemon Thread
        Thread daemonThread = new Thread(new DaemonTask());
        daemonThread.setDaemon(true);
        daemonThread.start();

        // Creating 2 User Threads
        Thread userThread1 = new Thread(new UserTask("User-Thread-1", 1000));
        Thread userThread2 = new Thread(new UserTask("User-Thread-2", 2000));

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        System.out.println("Main thread finished its task and exiting...");
    }
}



/// -------------------------------------- Inter thread communication ----------------------------------------
/// [definition] Inter-Thread Communication in Java allows multiple threads to coordinate their execution efficiently when working with shared resources.
///             Without proper communication, race conditions, data inconsistency, and wasted CPU cycles may occur.
/// java provides wait(), notify(), notifyAll() methods. these three methods present in Object class.
///
/// wait - When a thread calls wait(), it releases the lock on the object and enters the waiting state until another thread invokes notify() or notifyAll() on the same object.
/// when current thread in waiting state, other threads to acquire the lock and do their task.
/// notify() - This method wakes up a single thread that is waiting state.
/// notifyAll() - This method wakes up all threads that are waiting state.


// there are 3 version of wait() method -
// wait(): Pauses the thread indefinitely until another thread calls notify() or notifyAll() on the same object.
// wait(long timeout): Pauses the thread until notified, or until the specified timeout expires (in milliseconds). whichever happens first.
// wait(long timeout, int nanos): adding nanosecond for finer control, working is same.


/// ------------ wait() and sleep() diff ---------------
// wait() present in Object class while sleep() present in Thread class.
// wait() releases the object lock and enters waiting state while sleep() holds the lock (doesn't release).
// wait() requires notify() / notifyAll() to wake up and sleep() resumes after the specified time expires.
// wait() must be called under synchronized but sleep() doesn't require synchronization.

class Message {
    private String message;
    private boolean hasMessage = false;

    public synchronized void sendMessage(String msg) {
        while (hasMessage) {  // If a message is already sent, then wait for it to be read
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Inside catch block for sendMessage() method");
            }
        }
        this.message = msg;
        hasMessage = true;
        System.out.println("Sent: " + msg);
        notify();  // Notify the receiver thread
    }

    public synchronized void receiveMessage() {
        while (!hasMessage) {  // If no message is available, then wait
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Inside catch block for receiveMessage() method");
            }
        }
        System.out.println("Received: " + message);
        hasMessage = false;
        notify();  // Notify the sender thread
    }
}
class WaitNotifyExample {
    public static void main(String[] args) {
        Message msg = new Message();

        Thread sender = new Thread(() -> {
            String[] messages = {
                    "Hello", "How are you?", "How's your day going?", "Goodbye"
            };

            for (String m : messages) {
                msg.sendMessage(m);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Sender");

        Thread receiver = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                msg.receiveMessage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Receiver");

        sender.start();
        receiver.start();
    }
}



/// why functional interface introduced in java 8, type - 4 type
/// Intermediate statefull, stetefull dono hai, lazily
/// Terminal terminal op - eager

/// odd, evem print 2 Thread (executor service)

/// Thread class, Runnbale class (odd, evem print 2 Thread)

/// Future / CompletableFuture ??

/// CompletableFuture - (why it is introduced in java 8) ?

/// Thread safety (video) - volatile, AtomicInteger, synchronized keyword (block, method)

/// Thread safe - singleton class (synchronized)

/// synchrozed vs Reeentrantlock (theory)