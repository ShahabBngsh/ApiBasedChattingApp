package com.shahab.i180731_i180650;

public class SpecificChatRVModel {
    String message, time;
    int viewType;

    public SpecificChatRVModel(String message, String time, int viewType) {
        this.message = message;
        this.time = time;
        this.viewType = viewType;
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

    public int getViewType() {
        return viewType;
    }
}
