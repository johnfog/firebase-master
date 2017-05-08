package co.netguru.firebasemaster.chat.chatmessages;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.netguru.firebasemaster.R;
import co.netguru.firebasemaster.common.ui.BaseActivity;
import co.netguru.firebasemaster.config.Consts;

public class MessageActivity extends BaseActivity {

    public static void startActivity(Context context, String recipientId, String currentUserId, String chatRef) {
        final Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(Consts.EXTRA_RECIPIENT_ID, recipientId);
        intent.putExtra(Consts.EXTRA_CURRENT_USER_ID, currentUserId);
        intent.putExtra(Consts.EXTRA_CHAT_REF, chatRef);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);

        final String recipientId = getIntent().getStringExtra(Consts.EXTRA_RECIPIENT_ID);
        final String currentUserId = getIntent().getStringExtra(Consts.EXTRA_CURRENT_USER_ID);
        final String chatRef = getIntent().getStringExtra(Consts.EXTRA_CHAT_REF);


        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, MessageFragment.newInstance(recipientId, currentUserId, chatRef), MessageFragment.TAG).commit();
        }
    }

}
