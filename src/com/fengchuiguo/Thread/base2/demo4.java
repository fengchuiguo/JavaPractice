package com.fengchuiguo.Thread.base2;

/**
 * 以下暂无例子
 * 1，Thread.sleep() 线程的休眠
 * 2，setPriority()  线程的优先级
 * 3，Thread.yield() 线程的让步
 *
 * 本例子演示join
 * 4 join()
 * 线程的合并的含义就是将几个并行线程的线程合并为一个单线程执行，
 * 应用场景是当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 */
public class demo4 {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ThreadD t = new ThreadD();
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程第" + i + "次执行！");
            if (i > 2) {
                try {
                    //t线程合并到主线程中，主线程停止执行过程，转而执行t线程，直到t执行完毕后继续。
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Hello World!");
    }
}

class ThreadD extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程1第" + i + "次执行！");
        }
    }
}
