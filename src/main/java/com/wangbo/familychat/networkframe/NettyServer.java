package com.wangbo.familychat.networkframe;

import com.wangbo.familychat.networkframe.networkhandlers.ServerHandler;
import com.wangbo.familychat.networkframe.networkhandlers.WebSocketServerHandler;
import com.wangbo.familychat.networkframe.protocol.PacketDecoder;
import com.wangbo.familychat.networkframe.protocol.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {


    public static void start() {
        NioEventLoopGroup workers = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boss, workers)
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast("http-codec", new HttpServerCodec());
                                pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                                pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                                pipeline.addLast("handler", new WebSocketServerHandler());

                                pipeline.addLast(new PacketDecoder());
                                pipeline.addLast(new ServerHandler());
                                pipeline.addLast(new PacketEncoder());
                            }
                        }
                );
        bind(serverBootstrap, 8080);
    }

    public static void main(String[] args) {
        NettyServer.start();
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
