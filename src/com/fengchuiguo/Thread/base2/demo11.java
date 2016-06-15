package com.fengchuiguo.Thread.base2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列 和 阻塞栈
 * <p/>
 * 阻塞队列的概念是，一个指定长度的队列，如果队列满了，添加新元素的操作会被阻塞等待，直到有空位为止。
 * 同样，当队列为空时候，请求队列元素的操作同样会阻塞等待，直到有可用元素为止。
 * 有了这样的功能，就为多线程的排队等候的模型实现开辟了便捷通道，非常有用。可用于生产者消费者模式的一种实现。
 * <p/>
 * 阻塞队列还有更多实现类，用来满足各种复杂的需求：
 * ArrayBlockingQueue, DelayQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue
 * <p/>
 * 以下例子：
 * 可以看出，输出到元素19时候，就一直处于等待状态，因为队列满了，程序阻塞了。
 * 这里没有用多线程来演示
 * <p/>
 * ----------
 * <p/>
 * 阻塞栈，与阻塞队列相似。不同点在于栈是“后入先出”的结构，每次操作的是栈顶，而队列是“先进先出”的结构，每次操作的是队列头。
 * Java为阻塞栈定义了接口：java.util.concurrent.BlockingDeque，其实现类也比较多，具体可以查看JavaAPI文档。
 */
public class demo11 {
    public static void main(String[] args) throws InterruptedException {
////      阻塞队列
//        BlockingQueue blockingQueue = new ArrayBlockingQueue(20);
//        for (int i = 0; i < 30; i++) {
//            //将指定元素添加到此队列中，如果没有可用空间，将一直等待（如果有必要）。
//            blockingQueue.put(i);
//            System.out.println("向阻塞队列中添加了元素:" + i);
//        }
//        System.out.println("阻塞队列:程序到此运行结束，即将退出----");

//      阻塞栈
        BlockingDeque blockingDeque = new LinkedBlockingDeque(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此阻塞栈中，如果没有可用空间，将一直等待（如果有必要）。
            blockingDeque.putFirst(i);
            System.out.println("向阻塞栈中添加了元素:" + i);
        }
        System.out.println("阻塞栈:程序到此运行结束，即将退出----");

    }
}

class nothing{}
