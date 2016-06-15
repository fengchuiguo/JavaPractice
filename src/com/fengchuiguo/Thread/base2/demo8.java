package com.fengchuiguo.Thread.base2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁  普通锁
 * Lock
 * 在获取了锁对象后，用完后应该尽快释放锁，以便别的等待该锁的线程有机会去执行。
 */
public class demo8 {

    public static void main(String[] args) {

        //创建并发访问的账户
        MyCount myCount = new MyCount("oid001", 10000);

        //创建一个锁对象
        Lock lock = new ReentrantLock();
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User u1 = new User("张三", myCount, -4000, lock);
        User u2 = new User("张三儿子", myCount, 6000, lock);
        User u3 = new User("张三老婆", myCount, -8000, lock);
        User u4 = new User("张三", myCount, 800, lock);
        //在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        //关闭线程池
        pool.shutdown();
    }

}


/**
 * 信用卡的用户
 */
class User implements Runnable {
    private String name;     //用户名
    private MyCount myCount; //所要操作的账户
    private int iocash;      //操作的金额，当然有正负之分了
    private Lock myLock;     //执行操作所需的锁对象

    User(String name, MyCount myCount, int iocash, Lock myLock) {
        this.name = name;
        this.myCount = myCount;
        this.iocash = iocash;
        this.myLock = myLock;
    }

    public void run() {
        //获取锁
        myLock.lock();
        //执行现金业务
        System.out.println(name + "正在操作" + myCount + "账户，金额为" + iocash + "，当前金额为" + myCount.getCash());
        myCount.setCash(myCount.getCash() + iocash);
        System.out.println(name + "操作" + myCount + "账户成功，金额为" + iocash + "，当前金额为" + myCount.getCash());
        //释放锁，否则别的线程没有机会执行了
        myLock.unlock();
    }
}


/**
 * 信用卡账户，可随意透支
 */
class MyCount {
    private String oid;        //账号
    private int cash;            //账户余额

    MyCount(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "MyCount{" +
                "oid='" + oid + '\'' +
                ", cash=" + cash +
                '}';
    }
}