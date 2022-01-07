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


    //定义一个channel 组，管理所有的channel
    //GlobalEventExecutor.INSTANCE) 是全局的事件执行器，是一个单例
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //handlerAdded 表示连接建立，一旦连接，第一个被执行
    //将当前channel 加入到  channelGroup

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其它在线的客户端
        /*
        该方法会将 channelGroup 中所有的channel 遍历，并发送 消息，
        我们不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户端]" + channel.hashCode() + " 加入聊天" + sdf.format(new java.util.Date()) + " \n");
        channelGroup.add(channel);


    }

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
        User user = userService.findUserByID(channels.getKey(channel));
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
                System.out.println(jsonObject.getString("userid"));
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
