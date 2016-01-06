package com.fengchuiguo.BasicPractice;

/**
 * finalize() 方法
 * Java允许定义这样的方法，它在对象被垃圾收集器析构(回收)之前调用，这个方法叫做finalize( )，它用来清除回收对象。
 例如，你可以使用finalize()来确保一个对象打开的文件被关闭了。
 在finalize()方法里，你必须指定在对象销毁时候要执行的操作。
 finalize()一般格式是：
 protected void finalize()
 {
 // 在这里终结代码
 }
 关键字protected是一个限定符，它确保finalize() 方法不会被该类以外的代码调用。
 当然，Java的内存回收可以由JVM来自动完成。如果你手动使用，则可以使用上面的方法。
 * Created by Administrator on 2016/1/6 0006.
 */
public class demoMethod02 {

    public static void main(String args[]) {
        Cake c1 = new Cake(1);
        Cake c2 = new Cake(2);
        Cake c3 = new Cake(3);

        c2 = c3 = null;
        System.gc(); //调用Java垃圾收集器
    }
}

class Cake{
    private int id;
    public Cake(int id) {
        this.id = id;
        System.out.println("Cake Object " + id + " is created");
    }

    protected void finalize() throws java.lang.Throwable {
        super.finalize();
        System.out.println("Cake Object " + id + " is disposed");
    }
}
