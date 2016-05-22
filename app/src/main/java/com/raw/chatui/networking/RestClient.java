package com.raw.chatui.networking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raw.chatui.communication.Bus;
import com.raw.chatui.models.GroupChatResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

public class RestClient {

    private static final String TAG = RestClient.class.getSimpleName();
    private final RestInterface restInterface;

    public RestClient() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        RestAdapter restAdapter = new RestAdapter.Builder().setConverter(new GsonConverter(gson)).
                setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(NetworkConstants.BASE_ENDPOINT_URL).build();
        restInterface = restAdapter.create(RestInterface.class);
    }

    public void getMessages(@NonNull final Context context, @NonNull final Bus bus) {
        restInterface.getMessages(new Callback<GroupChatResponse>() {
            @Override
            public void success(GroupChatResponse groupChatResponse, Response response) {
                bus.post(groupChatResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO : Post error event so that there is retry option or correct error handling.
                Log.e(TAG, "Failed to get response for get messages");
                Toast.makeText(context, "Failure in network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface RestInterface {

        @GET("/android/test_data/")
        void getMessages(Callback<GroupChatResponse> response);
    }
}
