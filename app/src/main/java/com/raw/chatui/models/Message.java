package com.raw.chatui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {

    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("image-url")
    @Expose
    public String imageUrl;
    @SerializedName("message-time")
    @Expose
    public Date messageTime;

    @Override
    public boolean equals(Object o) {
        return o instanceof Message && ((Message) o).username.equals(username);
    }
}
