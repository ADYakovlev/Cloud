package com.yakovlev.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/*
 *@author Yakovlev Alexandr
 */
public class MyMessage implements Serializable {
    private static final long serialVersionUID = 5193392663743561680L;

    @Getter @Setter private String typeOf;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String userName;
    @Getter @Setter private String password;
    @Getter @Setter private String gender;
    @Getter @Setter private List pathList;
    @Getter @Setter private byte[] Byte;

    public MyMessage(String firstName, String lastName, String userName, String password, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public MyMessage() {}

}
