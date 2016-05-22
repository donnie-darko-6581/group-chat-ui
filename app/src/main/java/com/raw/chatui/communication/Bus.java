package com.raw.chatui.communication;

import android.support.annotation.NonNull;

import com.raw.chatui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

public class Bus {

    private final EventBus bus;

    public Bus() {
        bus =  EventBus.getDefault();
    }

    public void register(@NonNull BaseFragment fragment) {
        bus.register(fragment);
    }

    public void unregister(@NonNull BaseFragment object) {
        bus.unregister(object);
    }

    public void post(@NonNull Object object) {
        bus.post(object);
    }
}
