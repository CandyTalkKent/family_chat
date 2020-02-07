package com.wangbo.familychat.networkframe;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.wangbo.familychat.networkframe.protocol.LoginRequestPacket;
import com.wangbo.familychat.networkframe.protocol.MessagePacket;
import com.wangbo.familychat.networkframe.protocol.Packet;
import com.wangbo.familychat.pojo.User;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println("握手...");
        for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            System.out.println(key + ":" + shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        System.out.println("接收到消息：" + paramString);
    }


    @Override
    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        super.onWebsocketMessageFragment(conn, frame);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常" + e);

    }

    public static void main(String[] args) throws Exception {
        try {
            MyWebSocketClient client = new MyWebSocketClient("ws://127.0.0.1:8081/websocket");
            client.connect();
            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                System.out.println("还没有打开");
                Thread.sleep(1000);
            }
            System.out.println("建立websocket连接");

            login(client);

//           while (true){
//               sendMessage(client);
//               Thread.sleep(100000000);
//           }


            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendMessage(client);
                }
            }).start();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void login(MyWebSocketClient client) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setPassword("2121");
        byte[] bytes = getBytes(loginRequestPacket);
        client.send(bytes);
    }

    private static void sendMessage(MyWebSocketClient client) {

        MessagePacket messagePacket = new MessagePacket();
        messagePacket.setMessage("hello  nihaoddddsdadsssssssssseewefasdfasdfasdfsadfsadfsadfsadfsdafasdfsadf" +
                "adfasdfsadfsadfsadfsadfsadfsadfsdfsaasdfasdfasdaedfasdftyjyhrhjryhrtyhrt" +
                "rthrthrthrthrthrthrthrthrthrthrthrth" +
                "rthrthtrhrhreth" +
                "ergergegfasdfadfasdfasdfasdfasdfasdf" +
                "asdfasdfasfasdfasdfasfsdafasfasdfasdfasdfasdfasdfasfasdfasdfsafasdfasdfasdfasdfasdfadf  0000");
        User user = new User();
        user.setUserId(0l);

        messagePacket.setToUser(user);
        byte[] bytes = getBytes(messagePacket);


        client.send(bytes);




    }


    public static byte[] getBytes(Packet packet) {


        int strLength = JSON.toJSONString(packet).getBytes().length;


        NormalClient.MyResBytes myResBytes = new NormalClient.MyResBytes(2 + strLength);


        myResBytes.add(packet.getCommand());
        myResBytes.add((byte) 1);
        myResBytes.add(JSON.toJSONString(packet).getBytes());

        return myResBytes.getBytes();
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

        public byte[] getBytes() {
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

}