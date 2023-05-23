# RPC

手写简易版RPC框架

以下是运行成功的图：

Server端:
![img](https://github.com/qingchuanlarou/gk-rpc/raw/master/image/1.png)

Client端：
![img](https://github.com/qingchuanlarou/gk-rpc/raw/master/image/2.png)

## 项目架构分析

**1.common通用工具模块**

**2.serialize序列化模块**

> ​	序列化和反序列化实现，利用fastjson.JSON实现

**3.transport网络传输模块**

- ```java
  //处理网络请求的handle
  public interface RequestHandler {
      void onRequest(InputStream recive, OutputStream toRespon);
  }
  ```

- ```java
  //RPC server服务定义
  public interface TransportServer {
      //初始化Server服务
      void init(int port, RequestHandler handler);
      //开启Server服务
      void start();
      //关闭Server服务
      void stop();
  }
  ```

- ```java
  //客户端 也是服务消费者
  public interface TransportClient {
      //连接Server服务
      void connect(Peer peer);
      //订阅Server服务  并返回response
      InputStream write(InputStream data);
      //关闭
      void close();
  }
  ```

**4.Server模块**

> ​	主要调用网络传输模块中`HttpTransportServer`，将请求在Handle中实现，并封装在Response中。
>
> ​	服务注册，服务管理，服务发现的实现

**5.Client模块**

> ​	选择一个server端点连接，然后代理反射调用方法。

##### 不足和展望：

安全性：改为HTTPS，序列化进行加密，网络连接建立时进行深度验证

服务端处理能力：基于jettyserver来做的，自带线程池，用到线上的话，线程池最好自己做，同时返回数据通道做成队列形式

注册中心：锦上添花的内容，client自动发现server地址

集成能力：与springboot等框架做嵌入，例如做一个sprinboot-start自动创建server和client，通过beanfactory自动创建代理对象