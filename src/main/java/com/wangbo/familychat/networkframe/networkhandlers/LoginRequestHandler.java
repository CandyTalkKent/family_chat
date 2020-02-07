package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.Constant;
import com.wangbo.familychat.networkframe.protocol.LoginRequestPacket;
import com.wangbo.familychat.pojo.User;
import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        User user = loginRequestPacket.getUser();

        user = new User(); // 程序测试使用 init
        user.setUserId(0l);//程序测试使用 init


        final String password = loginRequestPacket.getPassword();

        //从数据库获取用户的密码与之匹配
        if (!validatePasswordFromDB(user, password)) {
            //登陆失败 todo
        }
        //登陆成功
        //标记这条channel已经登陆
        LoginUtils.markAsLogin(ctx.channel());

        List<ChannelHandlerContext> userChannelList = Constant.channelMap.get(user.getUserId());
        if (CollectionUtils.isEmpty(userChannelList)) {
            //说明没有一台设备登陆
            userChannelList = new ArrayList<>(2);
            userChannelList.add(ctx);
            Constant.channelMap.put(user.getUserId(), userChannelList);
        } else {

            userChannelList.add(ctx);
        }

        //登陆信息入库 异步 todo

        //通知客户端登陆成功 todo


    }

    private boolean validatePasswordFromDB(User user, String password) {

        return true;
    }
}
