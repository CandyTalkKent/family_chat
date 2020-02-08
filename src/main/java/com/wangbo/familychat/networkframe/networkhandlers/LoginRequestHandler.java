package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.Constant;
import com.wangbo.familychat.common.ResultData;
import com.wangbo.familychat.dao.entity.User;
import com.wangbo.familychat.networkframe.protocol.packet.LoginRequestPacket;
import com.wangbo.familychat.networkframe.protocol.packet.LoginResponsePacket;
import com.wangbo.familychat.services.impl.IUserService;
import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> implements ApplicationContextAware {


    private static IUserService userService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        User userFromClient = loginRequestPacket.getUser();


        User userFromDb = getUserFromDb(userFromClient.getPhone());
        if (userFromDb == null) {
            ctx.channel().writeAndFlush(ResultData.fail("没有此用户,请重新输入手机号"));
            return;
        }

        final String password = loginRequestPacket.getPassword();

        //从数据库获取用户的密码与之匹配
        if (!validatePassword(userFromDb.getPassword(), password)) {
            ctx.channel().writeAndFlush(ResultData.fail("密码错误，请重新输入"));
            return;
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

        ctx.channel().writeAndFlush(ResultData.success(loginResponsePacket));


    }

    private User getUserFromDb(String phone) {

        // todo
        User user = userService.getUserFromDbWithPhone(phone);
        return user;
    }

    private boolean validatePassword(String passwordFromDb, String password) {
        if (StringUtils.isEmpty(passwordFromDb) || StringUtils.isEmpty(password)) {
            return false;
        }

        return passwordFromDb.equals(password);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IUserService bean = applicationContext.getBean(IUserService.class);
        LoginRequestHandler.userService = bean;
    }
}
