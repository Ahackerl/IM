package com.netty.nettyclass;

import com.google.gson.Gson;
import com.netty.bean.Chat;
import com.netty.bean.User;
import com.netty.service.impl.ChatService;
import com.netty.service.impl.UserService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class GroupChatServerHandler implements ChannelInboundHandler {

    private final UserService userService;
    private final ChatService chatService;

    public GroupChatServerHandler(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    //使用一个hashmap 管理  channel--userId
    public static DoubleMap<String, Channel> channels = new DoubleMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {}

    //断开连接, 将xx客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel被移除");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("注册事件发生");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注销事件发生");
        Channel channel = ctx.channel();
        //通过ID定位 用户
        String key = channels.getKey(channel);
        User user = userService.findUserByID(key);
        channels.remove(key);
        //数据库更改状态为下线
        userService.updateOnlineStatusByUserName(user.getName(), 0);
        System.out.println("用户名：" + user.getName() + " 下线了~");
    }

    //表示channel 处于活动状态, 提示 xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    //表示channel 处于不活动状态, 提示 xx离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){

        try {
            ByteBuf buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            //把数据从byteBuf转移到byte[]
            buf.getBytes(0, bytes);
            //将byte[]转成字符串用于打印
            String tams = new String(bytes);

            JSONObject jsonObject = new JSONObject(tams);
            String type = jsonObject.getString("type");


            if("login".equals(type)){ //首次进入

                channels.put(jsonObject.getString("userid"),ctx.channel());

                //通过ID定位 用户
                User user = userService.findUserByID(jsonObject.getString("userid"));
                //数据库更改状态为上线
                userService.updateOnlineStatusByUserName(user.getName(), 1);
                System.out.println("用户名：" + user.getName() + " 上线了~");
            }


        }catch (JSONException ignored){}
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
