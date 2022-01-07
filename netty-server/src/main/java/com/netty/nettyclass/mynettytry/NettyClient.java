package com.netty.nettyclass.mynettytry;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            //创建客户端启动对象 BootStrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(eventExecutors)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道的实现类（反射）
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                        }
                    });
            System.out.println("Client is ready");
            //启动客户端去连接服务器端
            // ChannelFuture ---涉及到 netty 的异步模型
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 7001).sync();

            //给 关闭通道事件 增加监听  (非阻塞的)
            connect.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
