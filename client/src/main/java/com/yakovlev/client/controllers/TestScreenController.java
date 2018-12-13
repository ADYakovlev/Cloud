package com.yakovlev.client.controllers;

/*
 *@author Yakovlev Alexandr
 */

import com.yakovlev.client.Net;
import com.yakovlev.common.MyMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ResourceBundle;

public class TestScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldWorkDir;

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
    private Button btnDownload;

    @FXML
    private Button btnCreateDir;

    @FXML
    private TextField fieldSaveAs;

    @FXML
    void initialize() {

        btnTestCon.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setTypeOf("/test");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
            }).start();
        });
        btnAuth.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setUserName(fieldLog.getText().trim());
            myMessage.setPassword(fieldPass.getText().trim());

            myMessage.setTypeOf("/auth");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
            }).start();
        });

        btnSignUp.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setFirstName(fieldSFName.getText().trim());
            myMessage.setLastName(fieldSLName.getText().trim());
            myMessage.setUserName(fieldSUName.getText().trim());
            myMessage.setPassword(fieldSPass.getText().trim());
            myMessage.setGender(fieldSGender.getText().trim());

            myMessage.setTypeOf("/signup");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
            }).start();

        });
        btnAuth.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setUserName(fieldLog.getText().trim());
            myMessage.setPassword(fieldLog.getText().trim());

            myMessage.setTypeOf("/auth");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
            }).start();
        });

        btnCreateDir.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setTypeOf("/getfilelist");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
                List pathList = net.getAnswerMessage().getPathList();
                for (Object s : pathList) {
                    System.out.println(" - " + (String) s);
                }
            }).start();
        });

        btnDownload.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setTypeOf("/download");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
                System.out.println(net.getAnswerMessage().getTypeOf());
                byte[] data = net.getAnswerMessage().getByte();
                try {
                    Files.write(Paths.get(fieldWorkDir.getText().trim() + "/" + fieldSaveAs.getText().trim()), data, StandardOpenOption.CREATE_NEW);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        btnGetList.setOnAction(event -> {
            MyMessage myMessage = new MyMessage();
            myMessage.setTypeOf("/getfilelist");

            new Thread(() -> {
                Net net = new Net();
                net.messageExchange(myMessage);
            }).start();
        });

    }
}
