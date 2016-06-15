package com.fengchuiguo.Thread.ProducerConsumer;

/**
 * 生产者消费者模型中最简单的一种表示。
 * <p/>
 * 对于本例，要说明的是当发现不能满足生产或者消费条件的时候，调用对象的wait方法，wait方法的作用是释放当前线程的所获得的锁，
 * 并调用对象的notifyAll()方法，通知（唤醒）该对象上其他等待线程，使得其继续执行。这样，整个生产者、消费者线程得以正确的协作执行。
 * notifyAll() 方法，起到的是一个通知作用，不释放锁，也不获取锁。只是告诉该对象上等待的线程“可以竞争执行了，都醒来去执行吧”。
 * <p/>
 * 本例仅仅是生产者消费者模型中最简单的一种表示，
 * 本例中，如果消费者消费的仓储量达不到满足，而又没有生产者，则程序会一直处于等待状态，这当然是不对的。
 * 实际上可以将此例进行修改，修改为，根据消费驱动生产，同时生产兼顾仓库，如果仓不满就生产，并对每次最大消费量做个限制，
 * 这样就不存在此问题了，
 * 当然这样的例子更复杂，更难以说明这样一个简单模型。
 *
 * 也可以改为Lock 、await() 、signalAll()来实现（锁与条件变量），此处暂无例子。
 *
 */
public class Test1 {

    public static void main(String[] args) {
        Godown godown = new Godown(30);
        Consumer c1 = new Consumer(50, godown);
        Consumer c2 = new Consumer(20, godown);
        Consumer c3 = new Consumer(30, godown);
        Producer p1 = new Producer(10, godown);
        Producer p2 = new Producer(10, godown);
        Producer p3 = new Producer(10, godown);
        Producer p4 = new Producer(10, godown);
        Producer p5 = new Producer(10, godown);
        Producer p6 = new Producer(10, godown);
        Producer p7 = new Producer(80, godown);

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
class Godown {
    public static final int max_size = 100; //最大库存量
    public int curnum; //当前库存量

    public Godown() {
    }

    public Godown(int curnum) {
        this.curnum = curnum;
    }

    /**
     * 生产指定数量的产品
     *
     * @param neednum
     */
    public synchronized void produce(int neednum) {
        while (neednum + curnum > max_size) {
            System.out.println("要生产的产品数量" + neednum + "超过剩余库存量" + (max_size - curnum) + "，暂时不能执行生产任务!");
            try {
                //当前的生产线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //满足生产条件，则进行生产，这里简单的更改当前库存量
        curnum += neednum;
        System.out.println("已经生产了" + neednum + "个产品，现仓储量为" + curnum);
        //唤醒在此对象监视器上等待的所有线程
        notifyAll();

    }

    /**
     * 消费指定数量的产品
     *
     * @param neednum
     */
    public synchronized void consume(int neednum) {

        //不满足消费条件，库存不足
        while (curnum < neednum) {
            try {
                //当前的消费线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //满足消费条件，则进行消费，这里简单的更改当前库存量
        curnum -= neednum;
        System.out.println("已经消费了" + neednum + "个产品，现仓储量为" + curnum);
        //唤醒在此对象监视器上等待的所有线程
        notifyAll();

    }

}

/**
 * 生产者
 */
class Producer extends Thread {
    private int neednum;   //生产产品的数量
    private Godown godown; //仓库

    Producer(int neednum, Godown godown) {
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
class Consumer extends Thread {
    private int neednum;   //生产产品的数量
    private Godown godown; //仓库

    Consumer(int neednum, Godown godown) {
        this.neednum = neednum;
        this.godown = godown;
    }

    public void run() {
        //消费指定数量的产品
        godown.consume(neednum);
    }
}