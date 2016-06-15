package com.fengchuiguo.Thread.base2;

/**
 * 并发协作-死锁
 * 线程发生死锁可能性很小，即使看似可能发生死锁的代码，在运行时发生死锁的可能性也是小之又小。
 * 发生死锁的原因一般是两个对象的锁相互等待造成的。
 */
public class demo6 {
    public static void main(String[] args) {
        DeadlockRisk dead = new DeadlockRisk();
        MyThread t1 = new MyThread(dead, 1, 2);
        MyThread t2 = new MyThread(dead, 3, 4);
        MyThread t3 = new MyThread(dead, 5, 6);
        MyThread t4 = new MyThread(dead, 7, 8);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}


class MyThread extends Thread {
    private DeadlockRisk dead;
    private int a, b;


    MyThread(DeadlockRisk dead, int a, int b) {
        this.dead = dead;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        dead.read();
        dead.write(a, b);
    }
}

class DeadlockRisk {

    private static class Resource {
        public int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public int read() {
        synchronized (resourceA) {
            System.out.println("read():" + Thread.currentThread().getName() + "获取了resourceA的锁！");
            synchronized (resourceB) {
                System.out.println("read():" + Thread.currentThread().getName() + "获取了resourceB的锁！");
                return resourceB.value + resourceA.value;
            }
        }
    }

    public void write(int a, int b) {
        synchronized (resourceB) {
            System.out.println("write():" + Thread.currentThread().getName() + "获取了resourceB的锁！");
            synchronized (resourceA) {
                System.out.println("write():" + Thread.currentThread().getName() + "获取了resourceA的锁！");
                resourceA.value = a;
                resourceB.value = b;
            }
        }
    }

}
