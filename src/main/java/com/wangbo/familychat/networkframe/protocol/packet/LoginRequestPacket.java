package com.wangbo.familychat.networkframe.protocol.packet;

import com.wangbo.familychat.networkframe.protocol.Command;
import com.wangbo.familychat.dao.entity.User;
import lombok.Data;


@Data
public class LoginRequestPacket extends Packet {

    private User user;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
