package com.raw.chatui.module;

import com.raw.chatui.communication.Bus;
import com.raw.chatui.networking.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    @Singleton
    public RestClient getRestClient() {
       return new RestClient();
    }

    @Provides
    @Singleton
    public Bus getBus() {
        return new Bus();
    }
}
