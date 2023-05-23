package com.smgeek.gkrpc.server;

import com.segeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.Request;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service,
                         Request request){
            return ReflectionUtils.invoke(
                    service.getTarget(),
                    service.getMethod(),
                    request.getParameters()
            );
    }
}
