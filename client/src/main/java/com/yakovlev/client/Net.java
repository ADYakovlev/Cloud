package com.yakovlev.client;

import com.yakovlev.common.MyMessage;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import lombok.Getter;

import java.io.IOException;
import java.net.Socket;

public class Net {
    @Getter
    MyMessage answerMessage;
    ObjectEncoderOutputStream oeos = null;
    ObjectDecoderInputStream odis = null;

    public void messageExchange(MyMessage myMessage) {

        try (Socket socket = new Socket("localhost", 8180)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            odis = new ObjectDecoderInputStream(socket.getInputStream());

            oeos.writeObject(myMessage);
            oeos.flush();
            System.out.println("message sended...");

            answerMessage = (MyMessage) odis.readObject();
            System.out.println("message readed...");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                odis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oeos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
