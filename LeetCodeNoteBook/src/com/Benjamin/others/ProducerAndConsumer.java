package com.Benjamin.others;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:ProducerAndConsumer
 * Package:com.Benjamin.others
 * <p>
 * Description:
 * 生产者消费者模型plus
 *
 * @author: Benjamin
 * @date: 2020-08-18 14:54
 */
public class ProducerAndConsumer {

    /**
     * 缓冲区，用于存放生产者生产的物品
     */
    private static final List<Integer> BUFF = new LinkedList<>();

    /**
     * 最大生产数量
     */
    private static final int MAX_COUNT = 5;

    /**
     * 当前应该生产的物品id
     */
    private static int count = 1;

    private static class Producer implements Runnable {
        @Override
        public void run() {
            // 死循环无限生产
            while (true) {
                try {
                    Thread.sleep(1);
                    synchronized (BUFF) {
                        // 不能超过缓冲区
                        while (BUFF.size() >= MAX_COUNT) {
                            BUFF.wait();
                        }
                        // 得到生产的权限后，判断是否完成所有生产
                        // 如果是，则唤醒其他所有线程，本线程退出
                        if (count > 100) {
                            BUFF.notifyAll();
                            break;
                        }
                        // 生产一个物品，然后唤醒所有线程进行竞争
                        int id = count++;
                        BUFF.add(id);
                        System.out.println(Thread.currentThread().getName() + "-生产，id=" + id);
                        BUFF.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            // 无限制的从缓冲区中取物品进行消费
            A:
            while (true) {
                try {
                    synchronized (BUFF) {
                        // 缓冲区大小为0有两种情况，都是消费者将缓冲区中的所有物品都消费完了
                        // 但当生产者已经不再生产物品时需要特殊对待
                        while (BUFF.size() == 0) {
                            // 生产者已经不再生产物品，那么消费者也退出即可
                            if (count > 100) {
                                break A;
                            }
                            // 否则当前消费者等待即可
                            BUFF.wait();
                        }
                        // 拿到消费的权限后，消费一个物品，然后唤醒所有线程
                        int id = BUFF.remove(0);
                        System.out.println(Thread.currentThread().getName() + "-消费，id=" + id);
                        BUFF.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        Thread producer1 = new Thread(new Producer());
        producer1.setName("producer1");
        Thread producer2 = new Thread(new Producer());
        producer2.setName("producer2");
        Thread producer3 = new Thread(new Producer());
        producer3.setName("producer3");

        Thread consumer1 = new Thread(new Consumer());
        consumer1.setName("consumer1");
        Thread consumer2 = new Thread(new Consumer());
        consumer2.setName("consumer2");
        Thread consumer3 = new Thread(new Consumer());
        consumer3.setName("consumer3");
        Thread consumer4 = new Thread(new Consumer());
        consumer4.setName("consumer4");

        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();

        try {
            producer1.join();
            producer2.join();
            producer3.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
