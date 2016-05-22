package com.raw.chatui.ui;

import android.os.Bundle;

import com.raw.chatui.R;
import com.raw.chatui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getContainer() {
        return R.layout.activity_main;
    }

    @Override
    public int getRootId() {
        return R.id.root_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(GroupChatUiFragment.getInstance(getSupportFragmentManager(), null));
    }
}
