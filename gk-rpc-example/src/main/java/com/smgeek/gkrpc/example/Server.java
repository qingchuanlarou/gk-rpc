package com.smgeek.gkrpc.example;

import com.smgeek.gkrpc.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();

    }
}
