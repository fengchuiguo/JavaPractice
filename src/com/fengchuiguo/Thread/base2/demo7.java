package com.fengchuiguo.Thread.base2;

import java.util.concurrent.*;

/**
 * 线程池
 * <p/>
 * 一、固定大小的线程池
 * 二、单任务线程池
 * 三、可变尺寸的线程池
 * 四、延迟连接池
 * 五、单任务延迟连接池
 * 六、自定义线程池
 */
public class demo7 {

    public static void main(String[] args) {

        //一、固定大小的线程池:创建一个可重用固定线程数的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(2);

        //二、单任务线程池:创建一个使用单个 worker线程的 Executor，以无界队列方式来运行该线程。
//        ExecutorService pool = Executors.newSingleThreadExecutor();

        //三、可变尺寸的线程池:创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
//        ExecutorService pool = Executors.newCachedThreadPool();

        //四、延迟连接池:创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        //五、单任务延迟连接池:创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
//        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

        //六、自定义线程池
        //创建等待队列
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        //创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,2,TimeUnit.MILLISECONDS,bqueue);

        Thread t1 = new MyThread7();
        Thread t2 = new MyThread7();
        Thread t3 = new MyThread7();
        Thread t4 = new MyThread7();
        Thread t5 = new MyThread7();
        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);

        pool.execute(t4);
        pool.execute(t5);

        //四、五:使用延迟执行风格的方法
//        pool.schedule(t4, 10, TimeUnit.MILLISECONDS);
//        pool.schedule(t5,10, TimeUnit.MILLISECONDS);

        //关闭线程池
        pool.shutdown();
    }

}


class MyThread7 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}



//创建自定义线程池的构造方法很多，本例中参数的含义如下：
//public ThreadPoolExecutor(int corePoolSize,
//                          int maximumPoolSize,
//                          long keepAliveTime,
//                          TimeUnit unit,
//                          BlockingQueue<Runnable> workQueue)

//用给定的初始参数和默认的线程工厂及处理程序创建新的ThreadPoolExecutor。
//使用Executors工厂方法之一比使用此通用构造方法方便得多。

//参数：corePoolSize -池中所保存的线程数，包括空闲线程。
//        maximumPoolSize -池中允许的最大线程数。
//        keepAliveTime -当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
//        unit - keepAliveTime参数的时间单位。
//        workQueue -执行前用于保持任务的队列。此队列仅保持由execute方法提交的Runnable任务。

//抛出：IllegalArgumentException -如果 corePoolSize或 keepAliveTime小于零，或者 maximumPoolSize小于或等于零，
//                               或者 corePoolSize大于 maximumPoolSize。

//        NullPointerException -如果workQueue为 null
//
//        自定义连接池稍微麻烦些，不过通过创建的ThreadPoolExecutor线程池对象，
//        可以获取到当前线程池的尺寸、正在执行任务的线程数、工作队列等等。