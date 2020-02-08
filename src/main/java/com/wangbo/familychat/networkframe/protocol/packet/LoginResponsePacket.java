package com.wangbo.familychat.networkframe.protocol.packet;

import com.wangbo.familychat.pojo.User;
import lombok.Data;

import java.util.Date;


@Data
public class LoginResponsePacket extends Packet{

    private User user;

    private Date loginTime;

    private String device;
    @Override
    public Byte getCommand() {
        return null;
    }
}
