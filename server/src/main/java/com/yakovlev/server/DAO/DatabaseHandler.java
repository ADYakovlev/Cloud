package com.yakovlev.server.DAO;

import com.yakovlev.common.MyMessage;

import java.sql.*;

/*
 *@author Yakovlev Alexandr
 */
public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName+"?useLegacyDatetimeCode=false&serverTimezone=America/New_York";


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(MyMessage user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FRISTNAME + "," +
                Const.USERS_LASTNAME + "," +
                Const.USERS_USERNAME + "," +
                Const.USERS_PASSWORD + "," +
                Const.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?)";

        try {
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, user.getFirstName());
        prSt.setString(2, user.getLastName());
        prSt.setString(3, user.getUserName());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getGender());

        prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(MyMessage user){
        ResultSet resSet = null;

        String select = "SELECT * FROM "+ Const.USER_TABLE+" WHERE "+Const.USERS_USERNAME+"=? AND " +Const.USERS_PASSWORD+"=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}