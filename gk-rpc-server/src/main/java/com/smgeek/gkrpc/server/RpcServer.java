package com.smgeek.gkrpc.server;

import com.segeek.gkrpc.common.utils.ReflectionUtils;
import com.smgeek.gkrpc.Request;
import com.smgeek.gkrpc.Response;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.transport.RequestHandler;
import com.smgeek.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManger serviceManger;
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] bytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(bytes,Request.class);

                log.info("get Request {}", request);

                //通过request找到服务
                ServiceInstance sis = serviceManger.lookup(request);
                Object ret = serviceInvoker.invoke(sis,request);
                response.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("Rpc server get error: "
                        + e.getClass().getName()
                        + " : "+e.getMessage());
            }finally {
                byte[] resp = encoder.encode(response);
                try {
                    toResp.write(resp);
                    log.info("response client");
                } catch (Exception e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        // 用反射创建网络模块
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        // 序列化codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // 管理服务 service
        this.serviceManger = new ServiceManger();
        this.serviceInvoker = new ServiceInvoker();
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManger.register(interfaceClass, bean);
    }
}
