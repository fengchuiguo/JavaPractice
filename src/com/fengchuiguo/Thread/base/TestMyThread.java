package com.fengchuiguo.Thread.base;

public class TestMyThread {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        MyThread1 myThread1_1 = new MyThread1();
        MyThread1 myThread1_2 = new MyThread1();
        myThread1_1.start();
        myThread1_2.start();

        MyThread2 myThread2_1 = new MyThread2();
        Thread thread2 = new Thread(myThread2_1);
        thread2.start();

    }

}
