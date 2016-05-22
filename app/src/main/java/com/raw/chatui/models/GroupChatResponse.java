package com.raw.chatui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupChatResponse {

    @SerializedName("count")
    @Expose
    public int count;
    @SerializedName("messages")
    @Expose
    public List<Message> messages = new ArrayList<>();

}
