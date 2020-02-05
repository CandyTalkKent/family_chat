package com.wangbo.familychat.networkframe.protocol;
import lombok.Data;

@Data
public class MessagePacket extends  Packet{

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE;
    }

}
