package com.netty.nettyclass.mynettytry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    //通道就绪时————触发该方法
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client" + ctx);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","login");
        jsonObject.put("userid","61d6bbfd8a8de37ea4ece7f1");
        ctx.writeAndFlush(Unpooled.copiedBuffer(jsonObject.toString(), CharsetUtil.UTF_8));
    }

    @Override
    //通道有读取事件发生时————触发该方法
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器说：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址：" + ctx.channel().remoteAddress());
    }

    @Override
    //处理异常
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
