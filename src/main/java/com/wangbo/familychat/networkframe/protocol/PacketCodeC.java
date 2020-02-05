package com.wangbo.familychat.networkframe.protocol;

import com.wangbo.familychat.networkframe.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;

public class PacketCodeC {

    public static PacketCodeC INSTANCE = new PacketCodeC();


    private PacketCodeC() {

    }

    private static final int MAGIC_NUMBER = 0x123456;

    public Packet decode(ByteBuf bufIn) {

        int magicNumber = bufIn.readInt();

        byte version = bufIn.readByte();

        byte algorithm = bufIn.readByte();

        byte command = bufIn.readByte();

        int length = bufIn.readInt();

        byte[] bytes = new byte[length];
        bufIn.readBytes(bytes);


        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerializer(algorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte algorithm) {
        switch (algorithm){
            case 1:
                return Serializer.DEFAULT;
        }
        return Serializer.DEFAULT;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        Class<? extends Packet> aClass = null;
        try {
            switch (command) {
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


    public void encode(ByteBuf byteBuf, Packet packet) {
// 1. 创建 ByteBuf 对象
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(1);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);


    }
}
