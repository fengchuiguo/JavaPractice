package com.fengchuiguo.BasicPractice;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class Test02Arrays {

    public static void main(String[] args) {
        // 定义一个字符串数组
        String[] hobbys = { "sports", "game", "movie" };
        int[]  demos =new int[]{8,9,7,3,1};
        // 使用Arrays类的sort()方法对数组进行排序
        Arrays.sort(hobbys);
        // 使用Arrays类的toString()方法将数组转换为字符串并输出
        System.out.println(Arrays.toString(hobbys));

        Arrays.sort(demos);
        System.out.println(Arrays.toString(demos));


        System.out.println("======");
        String hel="你好啊";
//        String hel="hello";
        byte[] bytes=hel.getBytes();
        Arrays.sort(bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));

        System.out.println("------");
        char[] chars=hel.toCharArray();
        Arrays.sort(chars);
        System.out.println(Arrays.toString(chars));
        System.out.println(new String(chars));


        System.out.println("======");
        String[] strings={"哇","ha","哈"};
        List list=Arrays.asList(strings);//数组转为list
        System.out.println(list.size());
        System.out.println(list.get(0));


    }

}
