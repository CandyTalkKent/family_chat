package com.wangbo.familychat.common;

import com.wangbo.familychat.dao.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

    /**
     * 保存handshake 以便后续关闭
     */
    public static Map<String, WebSocketServerHandshaker> webSocketServerHandshakerMap = new ConcurrentHashMap<>();


    /**
     * 保存用户连接信息
     */
    public static Map<Long, List<ChannelHandlerContext>> channelMap = new ConcurrentHashMap<>();



    public static String channelMapKey(User user){
        return null;
    }

}
