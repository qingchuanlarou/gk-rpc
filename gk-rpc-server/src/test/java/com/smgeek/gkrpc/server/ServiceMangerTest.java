package com.smgeek.gkrpc.server;

import com.segeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceMangerTest {
    ServiceManger sm;

    @Before  //测试之前运行
    public void init(){
        sm = new ServiceManger();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.form(TestInterface.class, method);

        Request request = new Request();
        request.setService(sdp);

        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}