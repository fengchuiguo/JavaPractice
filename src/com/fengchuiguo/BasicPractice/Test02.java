package com.fengchuiguo.BasicPractice;

public class Test02 {
    public static void main(String[] args) {
        String s1="你好";
        String s2="你好";
        String s3=new String("你好");
        String s4=new String("你好");

        // == 判断内存地址
        System.out.println("s1==s2 结果 "+(s1==s2));//true【*】
        System.out.println("s1==s3 结果 "+(s1==s3));//false【*】
        System.out.println("s3==s4 结果 "+(s3==s4));//false【*】

        //equals 判断内容
        System.out.println("s1.equals(s2) 结果 "+s1.equals(s2));//true
        System.out.println("s1.equals(s3) 结果 "+s1.equals(s3));//true
        System.out.println("s3.equals(s4) 结果 "+s3.equals(s4));//true


        System.out.println("======");

        int i1=10;
        int i2=10;
        Integer i3=10;
        Integer i4=new Integer(10);
        Integer i5=new Integer("10");
        System.out.println(i1==i2);//true
        System.out.println(i1==i3);//true【】
        System.out.println(i1==i4);//true【】
        System.out.println(i3==i4);//false【】
        System.out.println(i1==i5);//true
        System.out.println(i4==i5);//false
        System.out.println(i3.equals(i4));//true

    }
}
