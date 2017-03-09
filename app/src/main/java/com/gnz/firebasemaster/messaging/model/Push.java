package com.gnz.firebasemaster.messaging.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Push {

    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("message")
    @Expose
    String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
