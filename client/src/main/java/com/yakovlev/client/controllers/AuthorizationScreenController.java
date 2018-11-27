package com.yakovlev.client.controllers;

/*
 *@author Yakovlev Alexandr
 */

import com.yakovlev.client.animations.Shake;
import com.yakovlev.common.MyMessage;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField fieldMail;

    @FXML
    void initialize() {
        btnLogin.setOnAction(event -> {
            if (!fieldMail.getText().trim().equals("") && !fieldPassword.getText().trim().equals("")) {
                if (sendAuthorizationData()) System.out.println("Data sending...");
            } else {
                Shake userLoginAnim = new Shake(fieldMail);
                Shake userPasswordAnim = new Shake(fieldPassword);
                userLoginAnim.playAnim();
                userPasswordAnim.playAnim();
            }
            sendAuthorizationData();
        });
    }

    private boolean sendAuthorizationData() {
        ObjectEncoderOutputStream oeos = null;
        ObjectDecoderInputStream odis = null;
        try (Socket socket = new Socket("localhost", 8180)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            MyMessage loginMessage = new MyMessage();
            loginMessage.setUserName(fieldMail.getText().trim());
            loginMessage.setPassword(fieldPassword.getText().trim());
            oeos.writeObject(loginMessage);
            oeos.flush();
            odis = new ObjectDecoderInputStream(socket.getInputStream());
            MyMessage msgFromServer = (MyMessage) odis.readObject();
            System.out.println("Answer from server: " + msgFromServer.getUserName()+":"+msgFromServer.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
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
        return true;
    }
}
