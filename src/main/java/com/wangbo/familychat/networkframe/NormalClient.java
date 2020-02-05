package com.wangbo.familychat.networkframe;

import com.alibaba.fastjson.JSON;
import com.wangbo.familychat.networkframe.protocol.MessagePacket;
import com.wangbo.familychat.networkframe.protocol.Packet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class NormalClient {


    public static byte[] getBytes(Packet packet) {

//        int strLength = packet.toString().getBytes().length;

        int strLength = JSON.toJSONString(packet).getBytes().length;


        MyResBytes myResBytes = new MyResBytes(4 + 1 + 1 + 1 + 4 + strLength);

        byte[] magicNumber = intToByte(0x123456);
        byte[] lengthBytes = intToByte(strLength);

        myResBytes.add(magicNumber);
        myResBytes.add((byte) 1);
        myResBytes.add((byte) 1);
        myResBytes.add(packet.getCommand());
        myResBytes.add(lengthBytes);
        myResBytes.add(JSON.toJSONString(packet).getBytes());

        return myResBytes.getBytes();
    }

    private void mapRes(byte[] res, byte[] source) {
        for (byte b : source) {

        }
    }


    static class MyResBytes {
        private int writeIndex = 0;
        private byte[] bytes;
        private int length;

        MyResBytes(int length) {
            assert length > 0;
            this.length = length;
            bytes = new byte[length];
        }

        public boolean add(byte b) {
            if (writeIndex > this.length - 1) {
                return false;
            }
            bytes[writeIndex++] = b;
            return true;
        }

        public int add(byte[] bytes) {
            int count = 0;
            for (byte b : bytes) {
                add(b);
                count++;
            }
            return count;
        }

        public byte[] getBytes(){
            return this.bytes;
        }
    }

    private static byte[] intToByte(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }


    public static void main(String[] args) {
        Socket socket = null;


        OutputStream oos = null;

        try {
            socket = new Socket("localhost", 8080);
            MessagePacket messagePacket = new MessagePacket();
            messagePacket.setMessage("hello  nihao");
//            String s = JSON.toJSONString(messagePacket);
            byte[] bytes = getBytes(messagePacket);
            socket.getOutputStream().write(bytes);
        } catch (Exception ex) {


        } finally {
            try {
                if (socket!= null){
                    socket.close();
                }

                if (oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
