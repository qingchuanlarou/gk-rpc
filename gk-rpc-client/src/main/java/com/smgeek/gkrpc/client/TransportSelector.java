package com.smgeek.gkrpc.client;

import com.smgeek.gkrpc.Peer;
import com.smgeek.gkrpc.transport.TransportClient;

import javax.sound.sampled.Port;
import java.util.List;

/**
 * 表示选择哪一个server做连接
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立的连接数
     * @param clazz client的实现类
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个Transport与server做连接
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     * @return
     */
    void release(TransportClient client);

    void close();
}
