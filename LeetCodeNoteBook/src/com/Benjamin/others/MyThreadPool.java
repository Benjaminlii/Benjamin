package com.Benjamin.others;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * ClassName:MyThreadPool
 * Package:com.Benjamin.others
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-11 上午10:20
 */
public class MyThreadPool {
    // 初始化线程的数量
    private int threadSize = 10;
    // 线程容器
    Set<ExecuteTask> set = new HashSet<>();
    // 任务容器
    LinkedList<Runnable> list = new LinkedList<Runnable>();

    // start这10个消费者线程
    public MyThreadPool() {
        for (int i = 0; i < threadSize; i++) {
            ExecuteTask thread = new ExecuteTask("Thread(" + i + "):");
            set.add(thread);
            thread.start();
        }
    }

    // 把任务加入任务容器，并唤醒执行线程来执行任务
    public void add(Runnable task) {
        synchronized (list) {
            list.add(task);
            list.notifyAll();
        }
    }

    public void clear() {
        for (ExecuteTask executeTask : set) {
            executeTask.interrupt();
        }
    }

    // 执行任务的线程
    private class ExecuteTask extends Thread {
        Runnable task;

        public ExecuteTask(String name) {
            super(name);
        }

        public void run() {
            System.out.println("启动" + this.getName());
            MARK:while (true) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        // 允许增加任务线程执行增加任务
                        try {
                            list.wait(100);
                        } catch (InterruptedException e) {
                            break MARK;
                        }
                    }
                    // 取出一个任务
                    task = list.removeLast();
                    // 允许增加任务线程执行增加任务
                    list.notifyAll();
                    System.out.println(this.getName() + "执行任务");
                }
                // 执行任务
                task.run();
            }
        }

    }

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool();

        for (int i = 0; i < 30; i++) {
            pool.add(new Runnable() {
                public void run() {
                    System.out.println("某任务.........");
                }
            });
        }
        pool.clear();
    }
}

