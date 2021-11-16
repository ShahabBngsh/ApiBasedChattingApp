package com.shahab.i180731_i180650;

public class ChatRVModel {
    String name, message, time, friend_id;
    boolean isOnline;
    public ChatRVModel(String name, String message, String time) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.isOnline = true;
        friend_id = "txJ7lgLHFHZJukJNue4zv7Uf6F13";
    }



    public ChatRVModel(String name, String message, String time, String user_id, boolean isOnline) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.friend_id = user_id;
        this.isOnline = isOnline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }
}

