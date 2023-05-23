package com.smgeek.gkrpc.example;

import com.smgeek.gkrpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1,2);
        int r2 = service.min(10,8);

        System.out.println(r1);
        System.out.println(r2);
    }
}
