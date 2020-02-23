package com.wangbo.familychat.networkframe.networkhandlers;

import com.wangbo.familychat.common.ChannelUserMapConstant;
import com.wangbo.familychat.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * 空闲检测，判断客户端连接是否失活
 */
public class IMIdleStateHandler extends IdleStateHandler {

    Logger logger = LoggerFactory.getLogger(IMIdleStateHandler.class);


    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        logger.info(READER_IDLE_TIME + "秒内未读到数据，关闭连接");


        if (LoginUtils.hasLogin(ctx.channel())) {//用户已经登陆
            //下线
            ChannelUserMapConstant.deleteUserAndChannelMap(ctx);
            //channel close
            ctx.channel().close();
            logger.info("连接成功关闭！");
        }

        ctx.channel().close();
    }
}