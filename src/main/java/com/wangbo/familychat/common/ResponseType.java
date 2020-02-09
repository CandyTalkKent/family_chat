package com.wangbo.familychat.common;

public class ResponseType {
    private String type;
    private String message;

    public ResponseType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public static ResponseType LOGIN_SUCCESS = new ResponseType("1", "login success");
    public static ResponseType MESSAGE_SUCCESS = new ResponseType("2", "message success");
    public static ResponseType FRIENDS_NOT_FOUND = new ResponseType("3", "friends not found");
    public static ResponseType FRIENDS_FOUND = new ResponseType("4", "friends  found");
    public static ResponseType REGISTER_SUCCESS = new ResponseType("5", "register  success");


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}