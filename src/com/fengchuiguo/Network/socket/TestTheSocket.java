package com.fengchuiguo.Network.socket;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class TestTheSocket {
    public static void main(String[] args) {

        String [] args_Server=new String[1];
        args_Server[0]="6066";
        GreetingServer.main(args_Server);

        String [] args_Client=new String[2];
        args_Client[0]="localhost";
        args_Client[1]="6066";
        GreetingClient.main(args_Client);

    }
}
