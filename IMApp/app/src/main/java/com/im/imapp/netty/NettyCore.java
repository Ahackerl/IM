package com.im.imapp.netty;

import android.util.Log;

import com.im.imapp.os.VarCons;

import org.json.JSONObject;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyCore {

    private Channel channel;


    // 注册Netty
    public void registerNetty() {
        new Thread(() -> {
            //进行初始化
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(); //初始化线程组
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class).group(nioEventLoopGroup);
            bootstrap.option(ChannelOption.TCP_NODELAY, true); //无阻塞
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true); //长连接
            bootstrap .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                }
            });

            //开始建立连接并监听返回
            ChannelFuture channelFuture = null;
            try {
                channelFuture = bootstrap.connect(new InetSocketAddress(VarCons.IP_NETTY, VarCons.PORT_NETTY)).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ChannelFuture finalChannelFuture = channelFuture;
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    Log.d("TAG", "与服务端连接成功 !");
                    this.channel = finalChannelFuture.channel();
                } else {
                    Log.d("TAG", "与服务端连接失败 !");
                }
            });
        }).start();
    }

}
