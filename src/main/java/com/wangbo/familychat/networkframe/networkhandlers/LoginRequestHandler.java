package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.Constant;
import com.wangbo.familychat.networkframe.protocol.packet.LoginRequestPacket;
import com.wangbo.familychat.networkframe.protocol.packet.LoginResponsePacket;
import com.wangbo.familychat.pojo.User;
import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        User userFromClient = loginRequestPacket.getUser();


        User userFromDb = getUserFromDb(userFromClient.getPhone());

        final String password = loginRequestPacket.getPassword();

        //从数据库获取用户的密码与之匹配
        if (!validatePassword(userFromDb.getPassword(), password)) {
            //登陆失败 todo
        }
        //登陆成功
        //标记这条channel已经登陆
        LoginUtils.markAsLogin(ctx.channel());

        List<ChannelHandlerContext> userChannelList = Constant.channelMap.get(userFromDb.getUserId());
        if (CollectionUtils.isEmpty(userChannelList)) {
            //说明没有一台设备登陆
            userChannelList = new ArrayList<>(2);
            userChannelList.add(ctx);
            Constant.channelMap.put(userFromDb.getUserId(), userChannelList);
        } else {

            userChannelList.add(ctx);
        }

        //登陆信息入库 异步 todo

        //通知客户端登陆成功

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setUser(userFromDb);
        loginResponsePacket.setDevice("");
        loginResponsePacket.setLoginTime(new Date());

        ctx.channel().writeAndFlush(loginResponsePacket);


    }

    private User getUserFromDb(String phone) {

        // todo

        User user = new User();
        user.setUserId(0l);
        return user;
    }

    private boolean validatePassword(String passwordFromDb, String password) {

        return true;
    }
}
