package com.fengchuiguo.Thread.base2;


/**
 * 线程交互 wait()、notifyAll() 简单使用
 * 运行结果表明（“没有使用runOk的时候”），程序中有异常，并且多次运行结果可能有多种输出结果。这就是说明，这个多线程的交互程序还存在问题。
 * 实际上，上面这个代码中，我们期望的是读取结果的线程在计算线程调用notifyAll()之前等待即可。
 * 但是，如果计算线程先执行，并在读取结果线程等待之前调用了notify()方法，那么又会发生什么呢？这种情况是可能发生的。
 * 因为无法保证线程的不同部分将按照什么顺序来执行。
 * 如果计算线程已经调用了notifyAll()方法，那么它就不会再次调用notifyAll()，并且等待的读取线程将永远保持等待。这当然是开发者所不愿意看到的问题。
 * 因此，当等待的事件发生时，需要能够检查notifyAll()通知事件是否已经发生。
 * 此处我使用runOk标识来区分了一下，检查notifyAll()通知事件是否已经发生。
 */
public class demo3 extends Thread {

    ThreadC c;

    public demo3(ThreadC c) {
        this.c = c;
    }

    public void run() {

        synchronized (c) {
            try {
                System.out.println(Thread.currentThread() + "等待计算结果。。。");
                if (!c.runOk) {
                    c.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "计算结果为：" + c.total);
        }

    }

    public static void main(String[] args) {

        System.out.println("Hello World!");

        ThreadC t = new ThreadC();

        //启动三个线程，分别获取计算结果
        new demo3(t).start();
        new demo3(t).start();
        new demo3(t).start();

        //启动计算线程
        t.start();

        System.out.println("Hello World!");
    }

}

class ThreadC extends Thread {
    int total;
    boolean runOk = false;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 101; i++) {
                total += i;
                System.out.println("i。。。" + i);
            }
            notifyAll();
            runOk = true;
        }
    }
}