package com.fengchuiguo.BasicPractice;

//“==” 和 equals() 有什么区别呢？
// ==: 判断两个字符串在内存中首地址是否相同，即判断是否是同一个字符串对象
// equals(): 比较存储在两个字符串对象中的内容是否一致
public class Test02equals{
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

        System.out.println("======");

        DemoClassObject demoClassObject1=new DemoClassObject(1,"xiaoming");
        DemoClassObject demoClassObject2=new DemoClassObject(1,"xiaoming");

        DemoClassObject demoClassObject3=demoClassObject1;
        System.out.println(demoClassObject1==demoClassObject2);//false
        System.out.println(demoClassObject1.equals(demoClassObject2));//false【】
        System.out.println(demoClassObject1==demoClassObject3);//true
        System.out.println(demoClassObject1.equals(demoClassObject3));//true

        System.out.println((demoClassObject1.getName())==(demoClassObject2.getName()));//true
//        demoClassObject2.setName(new String("xiaoming"));//如果这样子操作，则返回false。与String判断类似
//        System.out.println((demoClassObject1.getName())==(demoClassObject2.getName()));//false

        System.out.println((demoClassObject1.getName()).equals(demoClassObject2.getName()));//true


        System.out.println("======");

        DemoClassObject2 demoClassObject21=new DemoClassObject2(1,"xiaoming");
        DemoClassObject2 demoClassObject22=new DemoClassObject2(1,"xiaoming");

        DemoClassObject2 demoClassObject23=demoClassObject21;
        System.out.println(demoClassObject21==demoClassObject22);//false
        System.out.println(demoClassObject21.equals(demoClassObject22));//true【】因为DemoClassObject2类重写（Override）了equals方法
        System.out.println(demoClassObject21==demoClassObject23);//true
        System.out.println(demoClassObject21.equals(demoClassObject23));//true

        System.out.println((demoClassObject21.getName())==(demoClassObject22.getName()));//true
        System.out.println((demoClassObject21.getName()).equals(demoClassObject22.getName()));//true


    }
}
