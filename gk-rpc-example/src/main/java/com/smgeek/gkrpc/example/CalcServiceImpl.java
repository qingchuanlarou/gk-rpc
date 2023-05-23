package com.smgeek.gkrpc.example;

public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int min(int a, int b) {
        return a-b;
    }
}
