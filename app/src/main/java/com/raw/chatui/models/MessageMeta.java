package com.raw.chatui.models;

import android.graphics.Color;

/**
 * Meta data for extra information for a particular message.
 */
public class MessageMeta {

    public MessageMeta(int color) {
        this.color = color;
    }

    // Init default values
    public int color = Color.BLACK;
    public int messageCount = 1;
}
