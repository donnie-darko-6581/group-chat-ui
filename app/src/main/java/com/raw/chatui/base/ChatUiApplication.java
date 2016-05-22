package com.raw.chatui.base;

import android.app.Application;

import com.raw.chatui.module.ApplicationComponents;
import com.raw.chatui.module.DaggerApplicationComponents;

public class ChatUiApplication extends Application {

    ApplicationComponents components;

    @Override
    public void onCreate() {
        super.onCreate();
        components = DaggerApplicationComponents.builder().build();
    }
}
