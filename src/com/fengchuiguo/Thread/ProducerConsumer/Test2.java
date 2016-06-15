package com.fengchuiguo.Thread.ProducerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用阻塞队列实现生产者消费者模式
 *
 * 有时使用BlockingQueue可能会出现put()和System.out.println()输出不匹配的情况，这是由于它们之间没有同步造成的。
 * 当缓冲区已满，生产者在put()操作时，put()内部调用了await()方法，放弃了线程的执行，
 * 然后消费者线程执行，调用take()方法，take()内部调用了signal()方法，通知生产者线程可以执行，
 * 致使在消费者的println()还没运行的情况下生产者的println()先被执行，所以有了输出不匹配的情况。
 * 对于BlockingQueue大家可以放心使用，这可不是它的问题，只是在它和别的对象之间的同步有问题。
 */
public class Test2 {
    public static void main(String args[]) {
        Godown2 godown = new Godown2();


        Consumer2 c1 = new Consumer2(50, godown);
        Consumer2 c2 = new Consumer2(20, godown);
        Consumer2 c3 = new Consumer2(30, godown);
        Producer2 p1 = new Producer2(10, godown);
        Producer2 p2 = new Producer2(10, godown);
        Producer2 p3 = new Producer2(10, godown);
        Producer2 p4 = new Producer2(10, godown);
        Producer2 p5 = new Producer2(10, godown);
        Producer2 p6 = new Producer2(10, godown);
        Producer2 p7 = new Producer2(80, godown);

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}

/**
 * 仓库
 */
class Godown2 {
    public static final int max_size = 100; //最大库存量
    BlockingQueue<Object> queues = new LinkedBlockingQueue<Object>(max_size);

    public Godown2() {
    }

    /**
     * 生产指定数量的产品
     *
     * @param neednum
     */
    public void produce(int neednum) {

        // 如果仓库剩余容量为0
        if (queues.size() == max_size) {
            System.out.println("【库存量】:" + max_size + "/t暂时不能执行生产任务!");
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= neednum; ++i) {
            try {
                // 放入产品，如果满了，自动阻塞
                queues.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【现仓储量为】:" + queues.size());

    }

    /**
     * 消费指定数量的产品
     *
     * @param neednum
     */
    public void consume(int neednum) {

        // 如果仓库存储量不足
        if (queues.size() == 0) {
            System.out.println("【库存量】:0/t暂时不能执行生产任务!");
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= neednum; ++i) {
            try {
                // 消费产品，如果空了，自动阻塞
                queues.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("【现仓储量为】:" + queues.size());


    }

}

/**
 * 生产者
 */
class Producer2 extends Thread {
    private int neednum;   //生产产品的数量
    private Godown2 godown; //仓库

    Producer2(int neednum, Godown2 godown) {
        this.neednum = neednum;
        this.godown = godown;
    }

    public void run() {
        //生产指定数量的产品
        godown.produce(neednum);
    }
}

/**
 * 消费者
 */
class Consumer2 extends Thread {
    private int neednum;   //生产产品的数量
    private Godown2 godown; //仓库

    Consumer2(int neednum, Godown2 godown) {
        this.neednum = neednum;
        this.godown = godown;
    }

    public void run() {
        //消费指定数量的产品
        godown.consume(neednum);
    }
}