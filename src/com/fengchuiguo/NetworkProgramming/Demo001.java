package com.fengchuiguo.NetworkProgramming;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class Demo001 {
    public static void main(String[] args) {

//        获取本地ip
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(ip);
//        ---



    }
}
