package com.raw.chatui.utils;

import android.graphics.Color;
import android.util.Log;

import com.raw.chatui.models.Message;
import com.raw.chatui.models.MessageMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meta {

    // Map that holds user_id -> count + color  data
    public static HashMap<String, MessageMeta> map;

    public static void computeMetaAndSave(List<Message> messages) {

        // TODO : Just temporary, ideally generate random color for a key (user_id) and save it on shared prefs
        // TODO : This will help the messages from one user to be same coloured
        // TODO : This is a good link : http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Color.BLUE);
        list.add(Color.CYAN);
        list.add(Color.GREEN);
        list.add(Color.RED);
        list.add(Color.DKGRAY);
        list.add(Color.MAGENTA);
        list.add(Color.LTGRAY);
        list.add(Color.YELLOW);

        map = new HashMap<>();

        for (Message message : messages) {
            if (map.containsKey(message.username)) {
                MessageMeta messageMeta = map.get(message.username);
                messageMeta.messageCount++;
            } else {
                map.put(message.username, new MessageMeta(list.get(0)));
                list.remove(0);
            }
        }
    }

    public static String getMetaString() {
        StringBuilder builder = new StringBuilder();
        builder.append("user_name");
        builder.append("  -  ");
        builder.append("count");
        builder.append("\n");
        builder.append("---------------------");
        builder.append("\n");
        for (Map.Entry<String, MessageMeta> entry : map.entrySet()) {
            String userName = entry.getKey();
            MessageMeta meta = entry.getValue();
            builder.append(userName);
            builder.append("  -  ");
            builder.append(meta.messageCount);
            builder.append("\n");
        }
        return builder.toString();
    }
}
