package com.raw.chatui.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.raw.chatui.R;
import com.raw.chatui.base.BaseFragment;
import com.raw.chatui.models.GroupChatResponse;
import com.raw.chatui.ui.adapter.MessagesAdapter;
import com.raw.chatui.utils.Meta;
import com.raw.chatui.utils.Utils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class GroupChatUiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.messages_list) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG = GroupChatUiFragment.class.getSimpleName();
    private MessagesAdapter adapter;

    public static BaseFragment getInstance(@NonNull FragmentManager manager, @Nullable Bundle bundle) {
        GroupChatUiFragment fragment = (GroupChatUiFragment) manager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new GroupChatUiFragment();
        }
        if (bundle != null && !fragment.isAdded()) {
            Log.v(TAG, "Added bundle to the fragment");
            fragment.setArguments(bundle);
        } else if (bundle != null) {
            Log.w(TAG, "Bundle not added to the fragment");
        }
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initAdapter();
        makeNetworkRequest();
    }

    private void init() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stats:
                Utils.showStatsAlert(getBaseActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Makes a network request to fetch group chaat messages.
     */
    private void makeNetworkRequest() {
        client.getMessages(getBaseActivity(), bus);
    }

    private void initAdapter() {
        adapter = new MessagesAdapter(getBaseActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MessagesAdapter.MessageItemDecor(getResources().
                getDimensionPixelSize(R.dimen.dimen_medium_size)));
    }

    @Subscribe
    public void onEvent(GroupChatResponse event) {
        swipeRefreshLayout.setRefreshing(false);
        Meta.computeMetaAndSave(event.messages);
        adapter.addItems(event.messages);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
        //makeNetworkRequest();
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onRefresh() {
        makeNetworkRequest();
    }
}
