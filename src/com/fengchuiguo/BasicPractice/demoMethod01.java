package com.fengchuiguo.BasicPractice;

/**
 * java【可变参数方法】
 * JDK 1.5 开始，Java支持传递同类型的可变参数给一个方法。
 * 方法的可变参数的声明如下所示：
 * typeName... parameterName
 * Created by Administrator on 2016/1/6 0006.
 */
public class demoMethod01 {

    public static void main(String args[]) {
        // 调用可变参数的方法
        printMax(34, 3, 3, 2, 56.5);
        printMax(new double[]{1, 2, 3});

//        printMax2(34, 3, 3, 2, 56.5);//无法这样调用
        printMax2(new double[]{1, 2, 3});
    }



    public static void printMax(double...numbers) {
        if (numbers.length == 0) {
            System.out.println("No argument passed");
            return;
        }

        double result = numbers[0];

        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] > result)
                result = numbers[i];
        System.out.println("The max value is " + result);
    }

    public static void printMax2(double[] numbers) {
        if (numbers.length == 0) {
            System.out.println("No argument passed");
            return;
        }

        double result = numbers[0];

        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] > result)
                result = numbers[i];
        System.out.println("The max value is " + result);
    }

}
