package com.yakovlev.server;

import com.yakovlev.common.MyCommand;
import com.yakovlev.common.MyMessage;
import com.yakovlev.server.DAO.DatabaseHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            System.out.println(msg.getClass());
            if (msg instanceof MyMessage) {
                System.out.println("Client text massage: " + ((MyMessage) msg).getUserName());

                MyMessage answer = new MyMessage();
                answer.setUserName(loginUser(((MyMessage) msg).getUserName(), ((MyMessage) msg).getPassword()));
                ctx.write(answer);
                ctx.flush();
            } else if (msg instanceof MyCommand) {
                if (((MyCommand) msg).getCommand().equals("getList")){
                    MyMessage answer = new MyMessage();
                    answer.setList(CloudCommandHendler.fileList(((MyCommand) msg).getPath()));
                    ctx.write(answer);
                    ctx.flush();
                }
            }else{
                System.out.println("Server received wrong object!");
                return;
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    private String loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        MyMessage user = new MyMessage();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
//            loginSignInBtn.setOnAction(event -> {setScene("/sample/view/app.fxml");});
            return "Ok";

        } else {
//            Shake userLoginAnim = new Shake(loginTextField);
//            Shake userPasswordAnim = new Shake(passwordField);
//            userLoginAnim.playAnim();
//            userPasswordAnim.playAnim();
            return "no";
        }
    }
}
