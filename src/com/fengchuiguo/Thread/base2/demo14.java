package com.fengchuiguo.Thread.base2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 障碍器
 * 障碍器类，为了适应一种新的设计需求，
 * 比如一个大型的任务，常常需要分配好多子任务去执行，只有当所有子任务都执行完成时候，才能执行主任务，这时候，就可以选择障碍器了。
 * 障碍器是多线程并发控制的一种手段
 */
public class demo14 {
    public static void main(String[] args) {
        //创建障碍器，并设置MainTask为所有定数量的线程都达到障碍点时候所要执行的任务(Runnable)
        CyclicBarrier cb = new CyclicBarrier(7, new MainTask());
        new SubTask("A", cb).start();
        new SubTask("B", cb).start();
        new SubTask("C", cb).start();
        new SubTask("D", cb).start();
        new SubTask("E", cb).start();
        new SubTask("F", cb).start();
        new SubTask("G", cb).start();
    }
}


/**
 * 主任务
 */
class MainTask implements Runnable {

    @Override
    public void run() {
        System.out.println(">《主任务执行了！！》<");
    }

}

/**
 * 子任务
 */
class SubTask extends Thread {

    private String name;
    private CyclicBarrier cb;

    SubTask(String name, CyclicBarrier cb) {
        this.name = name;
        this.cb = cb;
    }

    public void run() {
        System.out.println("【子任务" + name + "】开始执行！");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[子任务" + name + "]执行完成了，并通知障碍器已经完成！");

        try {
//            通知障碍器已经完成
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

}