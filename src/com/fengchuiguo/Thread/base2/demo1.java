package com.fengchuiguo.Thread.base2;


/**
 * 同步问题提出
 * 线程的同步是为了防止多个线程访问一个数据对象时，对数据造成的破坏。
 * 例如：两个线程ThreadA、ThreadB都操作同一个对象Foo对象，并修改Foo对象上的数据。
 */
public class demo1 {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        MyRunnable r = new MyRunnable();
        Thread tA = new Thread(r, "Thread-A");
        Thread tB = new Thread(r, "Thread-B");
        tA.start();
        tB.start();

    }
}

class Foo {
    private int x = 100;

    public int getX() {
        return x;
    }

    public int fix(int y) {
        x = x - y;
        return x;
    }

}

class MyRunnable implements Runnable {

    private Foo foo = new Foo();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

//            未使用同步
//            foo.fix(30);
//            System.out.println(Thread.currentThread().getName() + " :当前foo对象的x值= " + foo.getX());


//            使用同步
            synchronized (this) {
                foo.fix(30);
                System.out.println(Thread.currentThread().getName() + " :当前foo对象的x值= " + foo.getX());
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}