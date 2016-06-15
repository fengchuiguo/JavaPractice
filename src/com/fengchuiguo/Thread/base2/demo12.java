package com.fengchuiguo.Thread.base2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量
 * 下面以一个银行存取款的模拟程序为例来揭盖Java多线程条件变量的神秘面纱：
 * 有一个账户，多个用户（线程）在同时操作这个账户，
 * 有的存款有的取款，存款随便存，取款有限制，不能透支，任何试图透支的操作都将等待里面有足够存款才执行操作。
 */
public class demo12 {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount12 myCount = new MyCount12("9988877665544", 10000);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread t1 = new SaveThread("张三", myCount, 2000);
        Thread t2 = new SaveThread("李四", myCount, 3600);
        Thread t3 = new DrawThread("王五", myCount, 2700);
        Thread t4 = new SaveThread("老张", myCount, 600);
        Thread t5 = new DrawThread("老牛", myCount, 1300);
        Thread t6 = new DrawThread("胖子", myCount, 800);
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        //关闭线程池
        pool.shutdown();
    }
}


/**
 * 存款线程类
 */
class SaveThread extends Thread {
    private String name;       //操作人
    private MyCount12 myCount; //账户
    private int x;             //存款金额

    SaveThread(String name, MyCount12 myCount, int x) {
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run() {
        myCount.saving(x, name);
    }
}

/**
 * 取款线程类
 */
class DrawThread extends Thread {
    private String name;       //操作人
    private MyCount12 myCount; //账户
    private int x;             //存款金额

    DrawThread(String name, MyCount12 myCount, int x) {
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run() {
        myCount.drawing(x, name);
    }
}

/**
 * 普通银行账户，不可透支
 */
class MyCount12 {
    private String oid;  //账号
    private int cash;    //账户余额
    private Lock lock = new ReentrantLock(); //账户锁
    private Condition _save = lock.newCondition();//存款条件
    private Condition _draw = lock.newCondition();//取款条件

    MyCount12(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     *
     * @param x    操作金额
     * @param name 操作人
     */
    public void saving(int x, String name) {
        lock.lock(); //获取锁
        if (x > 0) {
            cash += x;  //存款
            System.out.println(name + "存款" + x + "，当前余额为" + cash);
        }
        _draw.signalAll(); //唤醒所有等待的取款线程
        lock.unlock(); //释放锁
    }

    /**
     * 取款
     *
     * @param x    操作金额
     * @param name 操作人
     */
    public void drawing(int x, String name) {
        lock.lock();
        try {
            if (cash - x < 0) {
                _draw.await();  //余额不足，阻塞取款操作
            } else {
                cash -= x;  //取款
                System.out.println(name + "取款" + x + "，当前余额为" + cash);
            }
            _save.signalAll();  //唤醒所有存款操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


//假如我们不用锁和条件变量，如何实现此功能呢？下面是实现代码：

class MyCount12_2 {
    private String oid;                        //账号
    private int cash;                            //账户余额

    MyCount12_2(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     *
     * @param x    操作金额
     * @param name 操作人
     */
    public synchronized void saving(int x, String name) {
        if (x > 0) {
            cash += x;  //存款
            System.out.println(name + "存款" + x + "，当前余额为" + cash);
        }
        notifyAll();
    }

    /**
     * 取款
     *
     * @param x    操作金额
     * @param name 操作人
     */
    public synchronized void drawing(int x, String name) {
        try {
            if (cash - x < 0) {
                wait();
            } else {
                cash -= x;  //取款
                System.out.println(name + "取款" + x + "，当前余额为" + cash);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

}

//也可以将此例改为同步代码块来实现，方法名去掉 synchronized ；然后方法中使用 synchronized (this) {xx} ，此处不写实例了。

// 对比以上三种方式，
// 从控制角度上讲，
// 第一种（锁与条件变量）最灵活，
// 第二种（synchronized方法）代码最简单，
// 第三种（同步代码块）容易犯错。