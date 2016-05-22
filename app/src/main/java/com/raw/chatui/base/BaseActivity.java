package com.raw.chatui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.raw.chatui.communication.Bus;
import com.raw.chatui.networking.RestClient;

import javax.inject.Inject;

/**
 * Base class for application activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    public abstract int getContainer();

    @IdRes
    public abstract int getRootId();

    public void addFragment(@NonNull BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(getRootId(), fragment);
        fragmentTransaction.commit();
    }

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContainer());
    }

    @NonNull
    public ChatUiApplication getChatApplication() {
        if (getApplication() instanceof ChatUiApplication) {
            return (ChatUiApplication) getApplication();
        } else {
            throw new RuntimeException("Please add application in android manifest.");
        }
    }
}
