package com.netty.nettyclass.mynettytry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServerHandler
 * @Description
 * 1.我们自定义一个 Handler 需要继承 Netty 规定好的 某个 HandlerAdapter（处理适配器）
 * 2.符合 Netty 规则的 Handler 才能称之为 Handler ！！！
 * @Author SkySong
 * @Date 2021-02-28 21:45
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /* taskQueue （任务队列的使用）：防止耗时业务阻塞进程，任务队列的 channel 会被 异步执行。

            假如这里是一个比较 耗时 的业务！！！
            我们需要 异步执行 ，， 这会将 该 channel 提交到 对应 NIOEventLoop 的 taskQueue 里。
            debug：ctx->pipeline->channel->eventLoop->taskQueue
         */

        // 1. 普通实现方法
        ctx.channel().eventLoop().execute(() -> {

            try {
                // 线程睡五秒
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这个操作 会将这个任务 放入对应 channel 的 taskQueue 里
        });
        //后续放进去的任务 会 在 taskQueue 里排队，，这期间 “只有一个线程” 在按顺序执行队列里的任务。
        ctx.channel().eventLoop().execute(() -> {
            try {//等前面的 睡晚 5 秒 之后 ，，这里才开始睡。
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这个操作 会将这个任务 放入对应 channel 的 taskQueue 里
        });

        System.out.println("server ctx = " + ctx);
        //将 msg 转成 ByteBuf 方便操作
        //Bytebuf 是 Netty 提供的 性能更高的缓冲区，，并不是 NIO 的 ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());

        ChannelPipeline pipeline = ctx.pipeline();// 本质为双向链表（出栈入栈）


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到 缓冲区 并刷新
        //通常，我们会对发送给客户端的数据 进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hey~ 客户端", CharsetUtil.UTF_8));
    }

    //处理异常 ：一般是关闭通道

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
