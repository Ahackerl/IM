package com.netty.nettyclass.nettystart;

import com.netty.nettyclass.GroupChatServer;
import com.netty.service.impl.ChatService;
import com.netty.service.impl.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName NettyStart
 * @Description
 * @Author SkySong
 * @Date 2021-04-17 14:49
 */
@Component
public class NettyStart implements ApplicationRunner {

    @Resource
    private UserService userService;
    @Resource
    private ChatService chatService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new GroupChatServer(7001,userService,chatService).run();
    }
}
