package com.Benjamin.exam;

/**
 * ClassName:Didi20200326_1
 * Package:com.Benjamin
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-26 下午4:26
 */
public class Didi20200326_1 {

    private static int count = 0;
    private static boolean flag = false;

    public static void method1(){
        count++;
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flag){
                        method1();
                        flag = !flag;
                        Didi20200326_1.class.notifyAll();
                    }else{
                        try {
                            Didi20200326_1.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!flag){
                    method1();
                    flag = !flag;
                    Didi20200326_1.class.notifyAll();
                }else{
                    try {
                        Didi20200326_1.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
