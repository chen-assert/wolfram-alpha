package com.bdic;

import java.io.Serializable;

public class Message implements Serializable {
    public String code;
    public String class_name;
    public String result;

    public Message(String code, String class_name, String result) {
        this.code = code;
        this.class_name = class_name;
        this.result = result;
    }
}
