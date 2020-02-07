package com.wangbo.familychat.networkframe.protocol;

import com.wangbo.familychat.networkframe.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;

public class PacketCodeC {

    public static PacketCodeC INSTANCE = new PacketCodeC();


    private PacketCodeC() {

    }


    public Packet decode(ByteBuf bufIn) {


        byte type = bufIn.readByte();

        byte algorithm = bufIn.readByte();

        byte[] bytes = new byte[bufIn.readableBytes()];

        bufIn.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(type);

        Serializer serializer = getSerializer(algorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }


    private Serializer getSerializer(byte algorithm) {
        switch (algorithm) {
            case 1:
                return Serializer.DEFAULT;
        }
        return Serializer.DEFAULT;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        Class<? extends Packet> aClass = null;
        try {
            switch (command) {
                case 1:
                    aClass = LoginRequestPacket.class;
                    break;
                case 2:
                    aClass = MessagePacket.class;
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
        return aClass;
    }


    public byte[] encode(Packet packet) {

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        return  bytes;

    }
}
