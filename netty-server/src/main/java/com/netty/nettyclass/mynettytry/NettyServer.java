package com.netty.nettyclass.mynettytry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyServer
 * @Description Netty 服务器端
 * @Author SkySong
 * @Date 2021-02-28 16:32
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建 BossGroup 和 WorkerGroup
        /* 说明：
        1.创建两个线程组  bossGroup 和 workerGroup
        2.bossGroup 只处理连接请求，而真正 与 客户端对接业务的线程
        出自 workerGroup （由 workerGroup 完成）
        3.两个都是无限循环

        说明：bossGroup 和 workerGroup 中 含有的子线程个数，默认为： 实际 CPU核数 的 2 倍（CPU * 2）
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的 启动对象，，负责配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程来进行设置
            bootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//配置所用通道类型
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置连接状态为：保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                        //给 pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast((ChannelHandler) new NettyServerHandler());
                        }
                    }); //给我们的 workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println("....Server is ready....");

            //绑定一个端口并且同步，生成一个 ChannelFuture对象
            //启动服务器（并绑定端口）
            ChannelFuture cf = bootstrap.bind(6668).sync();

            //对  “关闭通道事件” 进行监听
            cf.channel().closeFuture().sync();
        } finally {
            //优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
