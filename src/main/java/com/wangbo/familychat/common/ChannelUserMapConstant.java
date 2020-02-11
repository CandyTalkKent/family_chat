package com.wangbo.familychat.common;

import com.wangbo.familychat.dao.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUserMapConstant {

    /**
     * 保存handshake 以便后续关闭
     */
    public static final Map<String, WebSocketServerHandshaker> webSocketServerHandshakerMap = new ConcurrentHashMap<>();


    /**
     * 保存用户连接信息
     */
    public static final Map<Long, List<ChannelHandlerContext>> channelMap = new ConcurrentHashMap<>();


    /**
     * 保存连接到用户的信息
     */
    public static  final  Map<ChannelHandlerContext,Long> channelToUserIdMap = new ConcurrentHashMap<>();




    public static  void mapUserIdAndChannel(ChannelHandlerContext ctx, User user){

        List<ChannelHandlerContext> userChannelList = ChannelUserMapConstant.channelMap.get(user.getUserId());
        if (CollectionUtils.isEmpty(userChannelList)) {
            //说明没有一台设备登陆
            userChannelList = new ArrayList<>(2);
            userChannelList.add(ctx);


            ChannelUserMapConstant.channelMap.put(user.getUserId(), userChannelList);
            ChannelUserMapConstant.channelToUserIdMap.put(ctx,user.getUserId());
        } else {

            userChannelList.add(ctx);
        }
    }


}
