package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.ChannelUserMapConstant;
import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);


    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        WebSocketFrame frame = (WebSocketFrame) msg;
        handlerWebSocketFrame(ctx, frame);
    }




    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // 关闭请求
        if (frame instanceof CloseWebSocketFrame) {

            WebSocketServerHandshaker handshaker = ChannelUserMapConstant.webSocketServerHandshakerMap.get(ctx.channel().id().asLongText());

            if (handshaker != null) {
                handshaker.close(ctx.channel(),
                        (CloseWebSocketFrame) frame.retain());
                ChannelUserMapConstant.webSocketServerHandshakerMap.remove(ctx.channel().id().asLongText());
            }

            return;
        }
        // ping请求
        if (frame instanceof PingWebSocketFrame) {
            logger.info("客户端发来的PingWebSocketFrame 心跳包");
//            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            ctx.fireChannelRead(frame.retainedDuplicate().content());
        }else if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame bf = (BinaryWebSocketFrame) frame;
            ctx.fireChannelRead(bf.retainedDuplicate().content());
        }
    }

}