package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.networkframe.protocol.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<MessagePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagePacket messagePacket) throws Exception {
        System.out.println(messagePacket.getMessage());
    }
}
