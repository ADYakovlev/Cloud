package com.yakovlev.server;

import com.yakovlev.common.MyCommand;
import com.yakovlev.common.MyMessage;
import com.yakovlev.server.DAO.DatabaseHandler;
import com.yakovlev.server.DAO.DatabaseMethods;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/*
 *@author Yakovlev Alexandr
 */
public class CloudServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg == null)
                return;
            if (msg instanceof MyMessage) {
                System.out.println("message");
                if (((MyMessage) msg).getTypeOf().equals("/signup")) {
                    DatabaseMethods.signUpUser((MyMessage) msg);
                    MyMessage answer = new MyMessage();
                    answer.setUserName("true");
                    ctx.write(answer);
                    ctx.flush();
                }
                if (((MyMessage) msg).getTypeOf().equals("/auth")) {
                    System.out.println("auth...");
                    String result = DatabaseMethods.getUser((MyMessage) msg);
                    System.out.println(result);
                    MyMessage answer = new MyMessage();
                    if (result != null) {
                        answer.setUserName(result);
                    } else {
                        answer.setUserName(null);
                    }
                    ctx.write(answer);
                    ctx.flush();
                }
            } else if (msg instanceof MyCommand) {
                System.out.println("command");
                if (((MyCommand) msg).getCommand().equals("/test")) {
                    System.out.println(((MyCommand) msg).getPath());
                    MyMessage answer = new MyMessage();
                    answer.setUserName("true");
                    ctx.write(answer);
                    ctx.flush();
                }
            } else {
                System.out.println("Server received wrong object!");
                return;
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
