package com.wangbo.familychat.networkframe.protocol.packet;

public abstract class Packet {


    /**
     * 协议版本
     */
    Byte version = 1;


    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
