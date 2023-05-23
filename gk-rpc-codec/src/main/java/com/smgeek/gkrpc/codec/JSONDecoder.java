package com.smgeek.gkrpc.codec;

import com.alibaba.fastjson.JSON;

import java.nio.channels.ClosedSelectorException;

/**
 * 基于JSON的反序列化实现
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
