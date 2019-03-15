package com.scoket.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyHandler extends ChannelInboundHandlerAdapter {//约定优于配置
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //msg  读取客户端的数据
        //ctx  写数据给客户端
    }
}
