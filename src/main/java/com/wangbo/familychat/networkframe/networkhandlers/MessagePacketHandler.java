package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.ChannelUserMapConstant;
import com.wangbo.familychat.common.ResponseType;
import com.wangbo.familychat.common.ResultData;
import com.wangbo.familychat.networkframe.protocol.packet.MessagePacket;
import com.wangbo.familychat.dao.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class MessagePacketHandler extends SimpleChannelInboundHandler<MessagePacket> {



    Logger logger = LoggerFactory.getLogger(MessagePacketHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messagePacket) throws Exception {


        User toUser = messagePacket.getToUser();

        //获取消息接受者的所有连接对象
        List<ChannelHandlerContext> toUserChannels = ChannelUserMapConstant.channelMap.get(toUser.getUserId());


        if (CollectionUtils.isEmpty(toUserChannels)) {//消息接受者未登陆
            // 消息缓存入redis

            return;
        }


        for (ChannelHandlerContext context : toUserChannels) {
            context.channel().writeAndFlush(ResultData.success(messagePacket, ResponseType.MESSAGE_SUCCESS));
        }

//
    }
}
