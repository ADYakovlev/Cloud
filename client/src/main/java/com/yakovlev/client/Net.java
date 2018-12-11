package com.yakovlev.client;

import com.yakovlev.common.MyCommand;
import com.yakovlev.common.MyMessage;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.IOException;

public class Net {

    public void sendObject(Object msg) {
        ObjectEncoderOutputStream oeos = null;
        ObjectDecoderInputStream odis = null;
        try (java.net.Socket socket = new java.net.Socket("localhost", 8180)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            oeos.writeObject(msg);
            oeos.flush();
            odis = new ObjectDecoderInputStream(socket.getInputStream());
            MyMessage msgFromServer = (MyMessage) odis.readObject();
            System.out.println("Answer from server: " + msgFromServer.getUserName() + "//.");
        } catch (Exception e) {
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


    public void test() {
        ObjectEncoderOutputStream oeos = null;
        ObjectDecoderInputStream odis = null;
        try (java.net.Socket socket = new java.net.Socket("localhost", 8180)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            MyCommand myCommand = new MyCommand("/test", "/test");
            oeos.writeObject(myCommand);
            oeos.flush();
            odis = new ObjectDecoderInputStream(socket.getInputStream());
            MyMessage msgFromServer = (MyMessage) odis.readObject();
            if(((MyMessage) odis.readObject()).getTypeOf()=="/file"){
               //save object
            }
            System.out.println("Answer from server: " + msgFromServer.getUserName() + "//.");
        } catch (Exception e) {
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
