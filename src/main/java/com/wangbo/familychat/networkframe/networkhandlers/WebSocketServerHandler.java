package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;

import java.util.logging.Logger;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger
            .getLogger(WebSocketServerHandler.class.getName());


    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        WebSocketFrame frame = (WebSocketFrame) msg;
        handlerWebSocketFrame(ctx, frame);
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // 关闭请求
        if (frame instanceof CloseWebSocketFrame) {

            WebSocketServerHandshaker handshaker = Constant.webSocketServerHandshakerMap.get(ctx.channel().id().asLongText());

            if (handshaker != null) {
                handshaker.close(ctx.channel(),
                        (CloseWebSocketFrame) frame.retain());
                Constant.webSocketServerHandshakerMap.remove(ctx.channel().id().asLongText());
            }

            return;
        }
        // ping请求
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }


        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame bf = (BinaryWebSocketFrame) frame;
            ctx.fireChannelRead(bf.retainedDuplicate().content());
        }
    }

}