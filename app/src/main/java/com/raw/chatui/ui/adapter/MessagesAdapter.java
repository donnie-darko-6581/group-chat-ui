package com.raw.chatui.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.raw.chatui.R;
import com.raw.chatui.base.BaseViewHolder;
import com.raw.chatui.models.Message;
import com.raw.chatui.models.MessageMeta;
import com.raw.chatui.utils.Utils;
import com.raw.chatui.utils.Meta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessagesAdapter extends RecyclerView.Adapter {

    private static final String TAG = MessagesAdapter.class.getSimpleName();
    private List<Message> messageList = new ArrayList<>();
    private Context context;

    public MessagesAdapter(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Message message = getItem(position);

        if (message == null) {
            Log.e(TAG, "Error getting item at position " + position + " not binding the view holder");
            return;
        }

        MessageMeta meta = Meta.map.get(message.username);

        // Currently there is only one view holder so we can directly cast it,
        // otherwise we can switch case using getViewType
        MessageHolder messageHolder = (MessageHolder) holder;

        messageHolder.name.setText(message.name);
        if (meta != null) {
            messageHolder.name.setTextColor(meta.color);
        }
        messageHolder.text.setText(message.body);
        messageHolder.time.setText(message.messageTime.toString());
        Utils.loadImage(context, messageHolder.image, message.imageUrl);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Nullable
    public Message getItem(int position) {
        if (position >= 0 && position < messageList.size()) {
            return messageList.get(position);
        } else {
            return null;
        }
    }

    public void addItems(@NonNull List<Message> messageList) {
        if (messageList.isEmpty()) {
            Log.w(TAG, "Did not add items in adapter because the list was empty");
            return;
        }
        this.messageList.clear();
        this.messageList.addAll(messageList);
        notifyDataSetChanged();
    }

    public static class MessageHolder extends BaseViewHolder {

        @BindView(R.id.avatar_image) RoundedImageView image;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.text) TextView text;
        @BindView(R.id.time) TextView time;

        public MessageHolder(View itemView) {
            super(itemView);
        }
    }

    public static class MessageItemDecor extends RecyclerView.ItemDecoration {

        private int spacing;

        public MessageItemDecor(int spacing) {
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            // Do not add spacing after the last item.
            int bottom = parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1 ? 0 : spacing;
            int right = 0;
            int top = 0;
            int left = 0;
            outRect.set(left, top, right, bottom);
        }
    }
}
