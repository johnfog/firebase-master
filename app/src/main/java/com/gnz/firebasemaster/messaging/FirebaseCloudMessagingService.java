package com.gnz.firebasemaster.messaging;


import android.util.Log;

import com.gnz.firebasemaster.messaging.model.Push;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    private final static String TAG = FirebaseMessagingService.class.getSimpleName();
    private static final Gson gson = new Gson();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject(remoteMessage.getData());
                final Push push = gson.fromJson(json.toString(), Push.class);
                handlePush(push);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void handlePush(Push push) {
        final NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.playNotificationSound();
        notificationUtils.showNotificationMessage(push.getTitle(), push.getMessage());

    }

}
