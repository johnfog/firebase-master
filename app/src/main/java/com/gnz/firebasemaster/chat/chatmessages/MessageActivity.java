package com.gnz.firebasemaster.chat.chatmessages;


import android.content.Context;
import android.content.Intent;

import com.gnz.firebasemaster.chat.chatusers.MainActivity;
import com.gnz.firebasemaster.common.ui.BaseActivity;

public class MessageActivity extends BaseActivity {

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
