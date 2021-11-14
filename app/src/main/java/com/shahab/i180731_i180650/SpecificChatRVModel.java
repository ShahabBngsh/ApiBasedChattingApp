package com.shahab.i180731_i180650;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpecificChatRVModel {
    String message, time;
    int viewType;

    public SpecificChatRVModel(){

    }

    public SpecificChatRVModel(String message, String time, int viewType) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
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
