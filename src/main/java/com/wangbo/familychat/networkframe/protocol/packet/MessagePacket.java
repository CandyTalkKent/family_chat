package com.wangbo.familychat.networkframe.protocol.packet;
import com.wangbo.familychat.networkframe.protocol.Command;
import com.wangbo.familychat.dao.entity.User;
import lombok.Data;

@Data
public class MessagePacket extends Packet {


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
