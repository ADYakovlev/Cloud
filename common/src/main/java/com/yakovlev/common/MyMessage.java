package com.yakovlev.common;

import java.io.Serializable;
import java.util.List;

/*
 *@author Yakovlev Alexandr
 */
public class MyMessage implements Serializable {
    private static final long serialVersionUID = 5193392663743561680L;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private List list;

    public MyMessage(String firstName, String lastName, String userName, String password, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public MyMessage() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public List getList() { return list; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setList(List list) { this.list = list; }
}
