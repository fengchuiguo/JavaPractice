package com.fengchuiguo.Network.socket;

import java.net.*;
import java.io.*;

/**
 * GreetingServer 程序是一个服务器端应用程序，使用Socket来监听一个指定的端口。
 * Created by Administrator on 2015/12/23 0023.
 */

public class GreetingServer extends Thread {

    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
//                System.out.println("GreetingServer>Waiting for client on port " + serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();
//                System.out.println("GreetingServer>Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("GreetingServer>" + in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "   Goodbye!");

                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("GreetingServer>Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new GreetingServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
