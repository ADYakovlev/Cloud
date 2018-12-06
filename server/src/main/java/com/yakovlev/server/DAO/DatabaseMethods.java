package com.yakovlev.server.DAO;

import com.yakovlev.common.MyMessage;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *@author Yakovlev Alexandr
 */
public class DatabaseMethods {

    public static void signUpUser(MyMessage msg) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        MyMessage myMessage = new MyMessage();
        myMessage.setFirstName(msg.getFirstName());
        myMessage.setLastName(msg.getLastName());
        myMessage.setUserName(msg.getUserName());
        myMessage.setPassword(msg.getPassword());
        myMessage.setGender(msg.getGender());

        dbHandler.signUpUser(myMessage);
    }

    public static String getUser(MyMessage msg) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        System.out.println("getUser...");
        MyMessage myMessage = new MyMessage();
        myMessage.setUserName(msg.getUserName());
        myMessage.setPassword(msg.getPassword());

        ResultSet resultSet = dbHandler.getUser(myMessage);
        String answer = resultSet.getString("idusers");
        return answer;
    }
}
