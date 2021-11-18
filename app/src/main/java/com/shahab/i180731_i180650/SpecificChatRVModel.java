package com.shahab.i180731_i180650;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpecificChatRVModel {
    String message, time;
    int viewType;
    int chattype;

    public SpecificChatRVModel(){

    }

    public SpecificChatRVModel(String message, String time, int viewType) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        this.message = message;
        this.time = time;
        this.viewType = viewType;
        this.chattype = 0;
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

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getChattype() {
        return chattype;
    }

    public void setChattype(int chattype) {
        this.chattype = chattype;
    }
}
