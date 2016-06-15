package com.fengchuiguo.Thread.base2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 从结果可以看出，信号量仅仅是对池资源进行监控，但不保证线程的安全，
 * 因此，在使用时候，应该自己控制线程的安全访问池资源。
 */
public class demo10 {
    public static void main(String[] args) {
        MyPool myPool = new MyPool(20);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        MyThread10 t1 = new MyThread10("任务A", myPool, 3);
        MyThread10 t2 = new MyThread10("任务B", myPool, 12);
        MyThread10 t3 = new MyThread10("任务C", myPool, 7);
        threadPool.execute(t1);
        threadPool.execute(t2);
        threadPool.execute(t3);
        threadPool.shutdown();
    }
}


/**
 * 一个池
 */
class MyPool {
    private Semaphore sp; //池相关的信号量

    /**
     * 池的大小，这个大小会传递给信号量。
     *
     * @param size 池的大小
     */
    MyPool(int size) {
        this.sp = new Semaphore(size);
    }

    public Semaphore getSp() {
        return sp;
    }

    public void setSp(Semaphore sp) {
        this.sp = sp;
    }
}

class MyThread10 extends Thread {
    private String threadname;
    private MyPool pool;
    private int x;

    MyThread10(String threadname, MyPool pool, int x) {
        this.threadname = threadname;
        this.pool = pool;
        this.x = x;
    }

    public void run() {
        try {
            //从此信号量获取给定数目的许可
            pool.getSp().acquire(x);
//          can doSomeThing
            System.out.println(threadname + "成功获取了" + x + "个许可！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放给定数目的许可，将其返回到信号量。
            pool.getSp().release(x);
            System.out.println(threadname + "释放了" + x + "个许可！");
        }
    }

}