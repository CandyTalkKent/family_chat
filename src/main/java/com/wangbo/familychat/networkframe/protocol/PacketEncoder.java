package com.wangbo.familychat.networkframe.protocol;

import com.alibaba.fastjson.JSON;
import com.wangbo.familychat.networkframe.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class PacketEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Packet) {
            String jsonString = JSON.toJSONString(msg);
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(jsonString);
            ctx.writeAndFlush(textWebSocketFrame);
        } else {

            super.write(ctx, msg, promise);
        }


    }


}
