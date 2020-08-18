package com.Benjamin.others;

/**
 * ClassName:DeadLock
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 死锁
 *
 * @author: Benjamin
 * @date: 2020-08-18 17:03
 */
public class DeadLock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    private static class Th1 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread1 want to get lock1...");
            synchronized (lock1){
                System.out.println("Thread1 got lock1");
                try {
                    Thread.sleep(200);
                    System.out.println("Thread1 want to get lock2...");
                    synchronized (lock2){
                        System.out.println("Thread1 got lock2");
                        // do something
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Th2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread2 want to get lock2...");
            synchronized (lock2){
                System.out.println("Thread2 got lock2");
                System.out.println("Thread2 want to get lock1...");
                synchronized (lock1){
                    System.out.println("Thread2 got lock1");
                    // do something
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(new Th1());
        Thread th2 = new Thread(new Th2());
        th1.start();
        Thread.sleep(100);
        th2.start();

        th1.join();
        th2.join();

    }
}
