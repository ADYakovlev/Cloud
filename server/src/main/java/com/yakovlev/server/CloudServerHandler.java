package com.yakovlev.server;

import com.yakovlev.common.MyMessage;
import com.yakovlev.server.DAO.DatabaseHandler;
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
                if (((MyMessage) msg).getTypeOf().equals("/download")) {
                    FileWorker fw = new FileWorker(ctx);
                    fw.sendfile();
                }
                if (((MyMessage) msg).getTypeOf().equals("/getfilelist")) {
                    FileWorker fw = new FileWorker(ctx);
                    fw.sendFileList();
                }
                if (((MyMessage) msg).getTypeOf().equals("/signup")) {
                    new DatabaseHandler().signUpUser((MyMessage) msg);
                    MyMessage answer = new MyMessage();
                    answer.setUserName("true");
                    ctx.write(answer);
                    ctx.flush();
                }
                if (((MyMessage) msg).getTypeOf().equals("/auth")) {
                    String result = new DatabaseHandler().getUser(((MyMessage) msg).getUserName(), ((MyMessage) msg).getPassword());
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
            } else {
                System.out.println("Server received wrong object!");
                return;
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
