package com.yakovlev.common;

import java.io.Serializable;

/*
 *@author Yakovlev Alexandr
 */
public class MyCommand implements Serializable {
    private String command;
    private String path;

    public MyCommand() {
    }

    public MyCommand(String command, String path) {
        this.command = command;
        this.path = path;
    }

    public String getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
