package com.yakovlev.server;

import com.yakovlev.common.MyMessage;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

/*
 *@author Yakovlev Alexandr
 */
public class FileWorker {
    ChannelHandlerContext ctx;

    public FileWorker(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public static void main(String[] args) throws IOException {
        try {
            Files.copy(Paths.get("2/a.txt"), Paths.get("1/a.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.move(Paths.get("2/a.txt"), Paths.get("2/c.txt"));
            Files.delete(Paths.get("2/a.txt"));
            List<String> stringList = Files.lines(Paths.get("2/a.txt"), StandardCharsets.UTF_8).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFileList() throws IOException {
        MyMessage ms = new MyMessage();
        List pathList = Files.list(Paths.get("3")).collect(Collectors.toList());
        ms.setTypeOf("/list");
//        ms.setPathList(pathList);
        ctx.write(ms);
        ctx.flush();
    }

    public void sendfile() throws IOException {
        MyMessage ms = new MyMessage();
        ms.setTypeOf("/file");
        ms.setByte(Files.readAllBytes(Paths.get("2/a.txt")));
        ctx.write(ms);
        ctx.flush();
    }
}
