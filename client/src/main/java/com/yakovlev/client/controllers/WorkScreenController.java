package com.yakovlev.client.controllers;

/*
 *@author Yakovlev Alexandr
 */

import com.yakovlev.common.MyCommand;
import com.yakovlev.common.MyMessage;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRe;

    @FXML
    private ListView<String> list;

    @FXML
    private Button btnUp;

    @FXML
    private Button btnDown;

    @FXML
    private Button btnGetList;

    @FXML
    void initialize() {
        btnGetList.setOnAction(event -> {
            MyMessage answer = sendCommand("getList", "./");
            ArrayList<String> arr = (ArrayList<String>) answer.getList();
            for (int i = 0; i < arr.size(); i++) {
                list.getItems().addAll(arr.get(i));
            }
        });

        btnUp.setOnAction(event -> { });
    }

    private MyMessage sendCommand(String command, String path) {
        ObjectEncoderOutputStream oeos = null;
        ObjectDecoderInputStream odis = null;
        MyMessage msgFromServer;
        try (Socket socket = new Socket("localhost", 8180)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            MyCommand myCommand = new MyCommand(command, path);
//            MyMessage loginMessage = new MyMessage();
//            loginMessage.setUserName(fieldMail.getText().trim());
//            loginMessage.setPassword(fieldPassword.getText().trim());
            oeos.writeObject(myCommand);
            oeos.flush();
            odis = new ObjectDecoderInputStream(socket.getInputStream());
            msgFromServer = (MyMessage) odis.readObject();
            System.out.println("Answer from server: " + msgFromServer.getList()+"//.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                oeos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                odis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return msgFromServer;
    }

    private MyMessage sendMessage(MyMessage msg){

//        ObjectEncoderOutputStream oeos = null;
//        ObjectDecoderInputStream odis = null;
//        MyMessage msgFromServer;
//        try (Socket socket = new Socket("localhost", 8180)) {
//            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
//
//            MyMessage message = new MyMessage();
//            loginMessage.setUserName(fieldMail.getText().trim());
//            loginMessage.setPassword(fieldPassword.getText().trim());
//            message.setUserName("Alexandr");
//            oeos.writeObject(message);
//            oeos.flush();
//            odis = new ObjectDecoderInputStream(socket.getInputStream());
//            msgFromServer = (MyMessage) odis.readObject();
//            System.out.println("Answer from server: " + msgFromServer.getList()+"//.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                oeos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                odis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
        return null;
    }
}

