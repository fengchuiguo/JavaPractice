package com.fengchuiguo.Thread.base;

/**
 * 继承Thread
 */
public class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread1 run()..." + i);
        }
    }
}
