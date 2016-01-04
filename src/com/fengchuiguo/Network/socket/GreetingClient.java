package com.fengchuiguo.Network.socket;

import java.net.*;
import java.io.*;

/**
 * GreetingClient 是一个客户端程序，该程序通过socket连接到服务器并发送一个请求，然后等待一个响应。
 * Created by Administrator on 2015/12/23 0023.
 */

public class GreetingClient {
    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
//            System.out.println("GreetingClient>Connecting to " + serverName + " on port " + port);

            Socket client = new Socket(serverName, port);
//            System.out.println("GreetingClient>Just connected to " + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("GreetingClient>Server says " + in.readUTF());

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
