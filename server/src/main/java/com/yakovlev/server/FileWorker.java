package com.yakovlev.server;

import com.yakovlev.common.MyMessage;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
 *@author Yakovlev Alexandr
 */
public class FileWorker {
    ChannelHandlerContext ctx;

    public FileWorker(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public static void main(String[] args) throws IOException {
        //работа с путями
        Path path = Paths.get("a.txt");

//        System.out.println("path: " + path);
//        System.out.println("path.getFileName(): " + path.getFileName());
//        System.out.println("path.normalize(): " + path.normalize());
//        System.out.println("path.getCountName(): "+path.getNameCount());
//        System.out.println("path.getname(3): " + path.getName(0));
//        System.out.println(path.getRoot());

        //работа с файлами
        try {
            Files.copy(Paths.get("2/a.txt"),Paths.get("1/a.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.move(Paths.get("2/a.txt"),Paths.get("2/c.txt"));
            Files.delete(Paths.get("2/a.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendfile() throws IOException {
        MyMessage ms = new MyMessage();
        ms.setTypeOf("/file");
        ms.setByte(Files.readAllBytes(Paths.get("2/a.txt")));
        ctx.write(ms);
        ctx.flush();
    }

}
