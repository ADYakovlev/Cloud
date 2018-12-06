package com.yakovlev.client.controllers;

/*
 *@author Yakovlev Alexandr
 */

import com.yakovlev.client.Net;
import com.yakovlev.common.MyCommand;
import com.yakovlev.common.MyMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TestScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGetList;

    @FXML
    private Button btnTestCon;

    @FXML
    private Button btnAuth;

    @FXML
    private TextField fieldPath;

    @FXML
    private Button btnSendFile;

    @FXML
    private TextField fieldLog;

    @FXML
    private TextField fieldPass;

    @FXML
    private ListView<?> listServerFile;

    @FXML
    private Button btnGetFile;

    @FXML
    private TextField fieldSFName;
    @FXML
    private TextField fieldSLName;

    @FXML
    private TextField fieldSUName;

    @FXML
    private TextField fieldSPass;

    @FXML
    private TextField fieldSGender;

    @FXML
    private Button btnSignUp;

    @FXML
    void initialize() {

        btnTestCon.setOnAction(event -> {
            new Thread(()->{Net.test();}).start();
        });
        btnAuth.setOnAction(event -> {
            MyCommand command = new MyCommand();
            command.setVarStr1(fieldLog.getText().trim());
            command.setVarStr2(fieldPass.getText().trim());

            command.setCommand("/auth");
            new Thread(()->{Net.sendObject(command);}).start();
        });

        btnSignUp.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setFirstName(fieldSFName.getText().trim());
            myMessage.setLastName(fieldSLName.getText().trim());
            myMessage.setUserName(fieldSUName.getText().trim());
            myMessage.setPassword(fieldSPass.getText().trim());
            myMessage.setGender(fieldSGender.getText().trim());

            myMessage.setTypeOf("/signup");
            new Thread(()->{Net.sendObject(myMessage);}).start();

        });
        btnAuth.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setUserName(fieldLog.getText().trim());
            myMessage.setPassword(fieldLog.getText().trim());

            myMessage.setTypeOf("/auth");
            new Thread(()->{Net.sendObject(myMessage);}).start();
        });

    }
}
