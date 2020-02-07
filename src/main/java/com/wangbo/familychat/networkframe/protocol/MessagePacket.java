package com.wangbo.familychat.networkframe.protocol;
import com.wangbo.familychat.pojo.User;
import lombok.Data;

@Data
public class MessagePacket extends  Packet{


    /**
     * 消息发送方
     */
    private User fromUser;

    /**
     * 消息接收方
     */
    private User toUser;

    /**
     * 消息
     */
    private String message;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE;
    }

}
