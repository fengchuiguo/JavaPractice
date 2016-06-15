package com.fengchuiguo.Thread.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的线程
 *
 * 可返回值的任务必须实现Callable接口，类似的，无返回值的任务必须Runnable接口。
 * 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了。
 */
public class MyThread3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        List<Future> futureList = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable callable = new MyCallable(i + "");
            //执行任务，并且获取Future对象
            Future future = pool.submit(callable);
            futureList.add(future);
        }
        //关闭线程池
        pool.shutdown();


        //获取所有并发任务的运行结果
        for (Future f : futureList) {
            //从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");

    }

}


class MyCallable implements Callable<Object> {

    private String taskNum;

    public MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }

}
