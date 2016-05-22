package com.raw.chatui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raw.chatui.communication.Bus;
import com.raw.chatui.networking.RestClient;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Lalit
 */
public abstract class BaseFragment extends Fragment {

    @Inject public RestClient client;
    @Inject public Bus bus;

    public abstract String getFragmentTag();

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseActivity().getChatApplication().components.inject(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public BaseActivity getBaseActivity() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        } else {
            throw new RuntimeException(getActivity() == null ? "The Activity is destroyed / null" :
                    "Hosting Activity should be instance of BaseActivity");
        }
    }
}
