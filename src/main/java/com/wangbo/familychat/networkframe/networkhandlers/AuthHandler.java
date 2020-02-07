package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (!LoginUtils.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            super.channelRead(ctx, msg);
        }

    }
}
