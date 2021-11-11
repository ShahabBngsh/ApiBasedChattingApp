package com.shahab.i180731_i180650;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;

public class Profile {
    String name, contact_no, profile_pic, online_status, last_seen, description;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Profile(String name, String contact_no, String profile_pic, String description) {
        this.name = name;
        this.contact_no = contact_no;
        this.profile_pic = profile_pic;
        this.online_status = "offline";
        this.last_seen = Calendar.getInstance().getTime().toString();
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
