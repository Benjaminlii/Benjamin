package com.Benjamin.exam;

/**
 * ClassName:Mogujie20200319_2
 * Package:com.Benjamin.exam
 * <p>
 * Description:
 * 通过多线程实现
 *
 * @author: Benjamin
 * @date: 20-3-20 下午3:40
 */
public class Mogujie20200319_2 {

    private static boolean flag = true;

    public static void methodA(){
        synchronized (Mogujie20200319_2.class){
            while (!flag){
                try {
                    Mogujie20200319_2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Run A!");
            flag = !flag;
            Mogujie20200319_2.class.notifyAll();
        }
    }
    public static void methodB(){
        synchronized (Mogujie20200319_2.class){
            while (flag){
                try {
                    Mogujie20200319_2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Run B!");
            flag = !flag;
            Mogujie20200319_2.class.notifyAll();
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                methodA();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                methodB();
            }
        });

        threadB.start();
        threadA.start();
    }

}
