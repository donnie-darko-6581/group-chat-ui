package com.raw.chatui.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.raw.chatui.R;
import com.squareup.picasso.Picasso;

public class Utils {

    //TODO : These are arbitrary and we can make these device density specific
    private static final int DEFAULT_WIDTH = 160;
    private static final int DEFAULT_HEIGHT = 160;

    /**
     * Loads image from url into the image view
     *
     * @param context   Context
     * @param imageView Image View in which we have to load the image
     * @param url       Url for image
     */
    public static void loadImage(@NonNull Context context, @Nullable ImageView imageView, @Nullable String url) {
        if (imageView != null && !TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).resize(getImageWidth(imageView), getImageHeight(imageView)).
                    placeholder(R.drawable.ghost).into(imageView);
        }
    }

    private static int getImageHeight(@NonNull ImageView imageView) {
        return imageView.getHeight() == 0 ? DEFAULT_HEIGHT : imageView.getHeight();
    }

    private static int getImageWidth(@NonNull ImageView imageView) {
        return imageView.getWidth() == 0 ? DEFAULT_WIDTH : imageView.getWidth();
    }

    public static void showStatsAlert(@NonNull Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.text_view, null);
        dialogBuilder.setView(view);
        view.setText(Meta.getMetaString());

        dialogBuilder.create().show();
    }
}
