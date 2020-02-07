package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.Constant;
import com.wangbo.familychat.networkframe.protocol.MessagePacket;
import com.wangbo.familychat.pojo.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class MessagePacketHandler extends SimpleChannelInboundHandler<MessagePacket> {

    Logger logger = LoggerFactory.getLogger(MessagePacketHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messagePacket) throws Exception {

        logger.info("message packet info message:{}", messagePacket.getMessage());

        User toUser = messagePacket.getToUser();

        //获取消息接受者的所有连接对象
        List<ChannelHandlerContext> toUserChannels = Constant.channelMap.get(toUser.getUserId());


        if (CollectionUtils.isEmpty(toUserChannels)) {//消息接受者未登陆
            // 消息缓存入redis

            return;
        }


        for (ChannelHandlerContext context : toUserChannels) {
//            context.writeAndFlush(messagePacket);
            context.channel().writeAndFlush(messagePacket);
        }

//
    }
}
