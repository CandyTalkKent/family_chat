package com.wangbo.familychat.networkframe.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class PacketEncoder extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        String jsonString = JSON.toJSONString(msg);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(jsonString);
        ctx.writeAndFlush(textWebSocketFrame);
    }
}
