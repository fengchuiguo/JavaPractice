package com.fengchuiguo.BasicPractice;

import java.util.Arrays;

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

    }

}
