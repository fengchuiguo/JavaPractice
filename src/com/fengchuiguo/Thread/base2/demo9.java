package com.fengchuiguo.Thread.base2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 在demo8中提到了Lock接口以及对象，使用它，很优雅的控制了竞争资源的安全访问，但是这种锁不区分读写，称这种锁为普通锁。
 * 为了提高性能，Java提供了读写锁，在读的地方使用读锁，在写的地方使用写锁，灵活控制，在一定程度上提高了程序的执行效率。
 * 下面这个例子是在demo8例子的基础上，将普通锁改为读写锁，并添加账户余额查询的功能。
 * 在实际开发中，最好在能用读写锁的情况下使用读写锁，而不要用普通锁，以求更好的性能。
 */
public class demo9 {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount9 myCount = new MyCount9("95599200901215522", 10000);
        //创建一个锁对象
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User9 u1 = new User9("张三", myCount, -4000, lock, false);
        User9 u2 = new User9("张三他爹", myCount, 6000, lock, false);
        User9 u3 = new User9("张三他弟", myCount, -8000, lock, false);
        User9 u4 = new User9("张三", myCount, 800, lock, false);
        User9 u5 = new User9("张三他爹", myCount, 0, lock, true);
        //在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        pool.execute(u5);
        //关闭线程池
        pool.shutdown();
    }
}


/**
 * 信用卡的用户
 */
class User9 implements Runnable {
    private String name;            //用户名
    private MyCount9 myCount;       //所要操作的账户
    private int iocash;             //操作的金额，当然有正负之分了
    private ReadWriteLock myLock;   //执行操作所需的锁对象
    private boolean ischeck;        //是否查询

    User9(String name, MyCount9 myCount, int iocash, ReadWriteLock myLock, boolean ischeck) {
        this.name = name;
        this.myCount = myCount;
        this.iocash = iocash;
        this.myLock = myLock;
        this.ischeck = ischeck;
    }

    public void run() {
        if (ischeck) {
            //获取读锁
            myLock.readLock().lock();
            System.out.println("读：" + name + " 正在查询" + myCount + "账户，当前金额为" + myCount.getCash());
            //释放读锁
            myLock.readLock().unlock();
        } else {
            //获取写锁
            myLock.writeLock().lock();
            //执行现金业务
            System.out.println("写：" + name + " 正在操作" + myCount + "账户，金额为" + iocash + "，当前金额为" + myCount.getCash());
            myCount.setCash(myCount.getCash() + iocash);
            System.out.println("写：" + name + " 操作" + myCount + "账户成功，金额为" + iocash + "，当前金额为" + myCount.getCash());
            //释放写锁
            myLock.writeLock().unlock();
        }
    }
}

/**
 * 信用卡账户，可随意透支
 */
class MyCount9 {
    private String oid;        //账号
    private int cash;            //账户余额

    MyCount9(String oid, int cash) {
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
