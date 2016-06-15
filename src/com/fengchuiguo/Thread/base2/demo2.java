package com.fengchuiguo.Thread.base2;


/**
 * 线程交互 wait()、notify() 简单使用
 */
public class demo2 {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        ThreadB tb = new ThreadB();

        tb.start();

        //demo2 main线程 拥有tb对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
        synchronized (tb) {
            System.out.println("等待对象b完成计算。。。");
            try {
                System.out.println("tb.wait()准备调用。。。");
                //当前线程等待
                tb.wait();
                System.out.println("tb.wait()结束调用。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("b对象计算的总和是：" + tb.total);

    }

}

class ThreadB extends Thread {
    int total;

    public void run() {
        System.out.println("对象b开始计算。。。");
        synchronized (this) {
            System.out.println("对象b开始计算synchronized。。。");
            for (int i = 0; i < 101; i++) {
                total += i;
                System.out.println("i。。。" + i);
            }
            System.out.println("对象b完成计算。。。准备通知");
            //（完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程demo2被唤醒
            notify();
            System.out.println("对象b完成计算。。。完成通知");
        }
    }
}