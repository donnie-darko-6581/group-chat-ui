package com.raw.chatui.module;

import com.raw.chatui.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ApplicationComponents {

    void inject(BaseFragment fragment);
}
