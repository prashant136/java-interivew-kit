package _01_CoreJava.multithreading;

class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        // jab hasData == false -> toh produce hoga.
        while(hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("produce: " + value);
        notify();
    }

    public synchronized int consume() {
        // jab hasData == true -> tah consume hoga.
        while(!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("consume: " + data);
        notify();
        return data;
    }
}

class Producer extends Thread {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.produce(i);
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int val = resource.consume();
        }
    }
}

public class $13ThreadCommunication {
    void main() {
        SharedResource sharedResource = new SharedResource();
        Thread producerThread = new Thread(new Producer(sharedResource));
        Thread consumerThread = new Thread(new Consumer(sharedResource));

        producerThread.start(); consumerThread.start();
    }
}
