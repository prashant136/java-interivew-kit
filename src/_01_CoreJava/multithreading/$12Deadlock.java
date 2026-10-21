package _01_CoreJava.multithreading;

class Pen {
    public synchronized void writeWithPenPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper {
    public synchronized void writeWithPenPaper(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to");
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 extends Thread {
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenPaper(paper);   // thread-1 lock pen and tries to lock paper
    }
}

class Task2 extends Thread {
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    // incorrect (deadlock) -
//    @Override
//    public void run() {
//        paper.writeWithPenPaper(pen);   // thread-2 lock paper and tries to lock pen
//    }

    @Override
    public void run() {
        synchronized (pen) {
            ///  pahle pen ka lock lao then paper ka lock request karo
            paper.writeWithPenPaper(pen);
        }
    }
}

public class $12Deadlock {
    void main() {
        Pen pen = new Pen();
        Paper paper = new Paper();
        Thread th1 = new Thread(new Task1(pen, paper));
        Thread th2 = new Thread(new Task2(pen, paper));
        th1.start(); th2.start();
    }
}

/// how to resolve -
/// sare threads ek consistent order me locks ko acquire kare.
/// multiple thread (jo chah rhe h lock ko aquire krna), they should always acquire the resource in the same sequence.