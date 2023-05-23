package com.smgeek.gkrpc;

import lombok.Data;

/*
    表示一个请求
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
