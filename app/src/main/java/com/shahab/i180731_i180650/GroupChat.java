package com.shahab.i180731_i180650;

public class GroupChat {
    String name, message, time;

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

    public GroupChat(String name, String message, String time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }


}
