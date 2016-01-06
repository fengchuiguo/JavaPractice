package com.fengchuiguo.BasicPractice;

public class Test01 {
    public static void main(String[] args) {
//        b=a++(先将a赋值给b，然后a再+1)
        int a = 1;
        int b = 1;
        b = a++;
        System.out.println("a:" + a);
        System.out.println("b:" + b);

//        d=++c(先a+1,然后将a赋值给b)
        int c = 1;
        int d = 1;
        d = ++c;
        System.out.println("c:" + c);
        System.out.println("d:" + d);

    }
}
