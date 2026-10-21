package _01_CoreJava.streams;

class MyThread extends Thread {
  @Override
  public void run() {
    try {
      System.out.println("Running");
      Thread.sleep(2000);
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        Thread.sleep(100);
        System.out.println(t.getState());
        t.join();
        System.out.println(t.getState());
    }
}