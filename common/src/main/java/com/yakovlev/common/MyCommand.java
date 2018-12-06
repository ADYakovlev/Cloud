package com.yakovlev.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
 *@author Yakovlev Alexandr
 */
public class MyCommand implements Serializable {
    private static final long serialVersionUID = 5193392663743561680L;

    @Getter @Setter private String command;
    @Getter @Setter private String path;
    @Getter @Setter private String varStr1;
    @Getter @Setter private String varStr2;

    public MyCommand() {
    }

    public MyCommand(String command, String path) {
        this.command = command;
        this.path = path;
    }

}
