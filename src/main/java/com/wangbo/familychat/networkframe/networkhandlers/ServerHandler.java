package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.networkframe.protocol.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends SimpleChannelInboundHandler<MessagePacket> {

    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messagePacket) throws Exception {
        logger.info("message packet info message:{}", messagePacket.getMessage());

        MessagePacket packet = new MessagePacket();
        packet.setMessage("server received message " + messagePacket.getMessage());

        ctx.fireChannelRead(packet);
    }
}
